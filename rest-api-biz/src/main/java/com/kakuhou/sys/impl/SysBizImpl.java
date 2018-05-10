package com.kakuhou.sys.impl;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.kakuhou.annotation.Unencrypt;
import com.kakuhou.base.Msg;
import com.kakuhou.config.RequestMappingHandlerConfig;
import com.kakuhou.constant.CodeConst;
import com.kakuhou.constant.DataSourceConst;
import com.kakuhou.dao.datasource.DataSource;
import com.kakuhou.dao.generator.bean.RestClientPO;
import com.kakuhou.dao.generator.bean.RestClientPOExample;
import com.kakuhou.dao.generator.bean.RestInterfacePO;
import com.kakuhou.dao.generator.bean.RestInterfacePOExample;
import com.kakuhou.dao.generator.bean.RestRightPO;
import com.kakuhou.dao.generator.mapper.RestClientPOMapper;
import com.kakuhou.dao.generator.mapper.RestInterfacePOMapper;
import com.kakuhou.dao.generator.mapper.RestRightPOMapper;
import com.kakuhou.exception.BizException;
import com.kakuhou.sys.ISysBiz;
import com.kakuhou.utils.Base64Utils;
import com.kakuhou.utils.RsaUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统业务实现
 * 
 * @author guopeng
 */
@Slf4j
@Service
public class SysBizImpl implements ISysBiz {
	@Autowired
	private RestClientPOMapper clientPOMapper;
	@Autowired
	private RestRightPOMapper rightPOMapper;
	@Autowired
	private RestInterfacePOMapper interfacePOMapper;
	@Autowired
	RequestMappingHandlerConfig requestMappingHandlerConfig;
	@Value("${Owner.ClientId}")
	private String OwnerClientId;

	@DataSource(DataSourceConst.DATASOURCE_MASTER)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addRight(String desc) {
		RestRightPO record = new RestRightPO();
		record.setDescription(desc);
		rightPOMapper.insertSelective(record);
	}

	@DataSource(DataSourceConst.DATASOURCE_MASTER)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void initInterface() {
		RequestMappingHandlerMapping bean = requestMappingHandlerConfig.requestMappingHandlerMapping();
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
		Set<RequestMappingInfo> infos = handlerMethods.keySet();
		infos.forEach((info) -> {
			HandlerMethod method = handlerMethods.get(info);
			Unencrypt unencrypt = method.getMethodAnnotation(Unencrypt.class);
			PatternsRequestCondition prc = info.getPatternsCondition();
			Set<String> patterns = prc.getPatterns();
			patterns.forEach(mapper -> {
				RestInterfacePOExample example = new RestInterfacePOExample();
				example.createCriteria().andInterfaceUriEqualTo(mapper);
				List<RestInterfacePO> interfacePOs = interfacePOMapper.selectByExample(example);
				if (CollectionUtils.isEmpty(interfacePOs)) {
					RestInterfacePO po = new RestInterfacePO();
					po.setEncrypted(unencrypt == null ? true : false);
					po.setInterfaceUri(mapper);
					interfacePOMapper.insertSelective(po);
				} else {
					RestInterfacePO interfacePO = interfacePOs.get(0);
					if (interfacePO.getEncrypted() != (unencrypt == null ? true : false)) {
						interfacePO.setEncrypted(unencrypt == null ? true : false);
						interfacePO.setUpdateTime(new Date());
						interfacePOMapper.updateByPrimaryKey(interfacePO);
					}
				}
			});
		});
	}

	@Override
	public boolean isEncrypted(String uri) {
		RestInterfacePOExample example = new RestInterfacePOExample();
		example.createCriteria().andInterfaceUriEqualTo(uri);
		List<RestInterfacePO> list = interfacePOMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0).getEncrypted();
		}
		return false;
	}

	@Override
	public boolean isClientLegal(String clientId) {
		if (StringUtils.isEmpty(clientId)) {
			return false;
		}
		RestClientPOExample example = new RestClientPOExample();
		example.createCriteria().andIdEqualTo(clientId);
		if (clientPOMapper.countByExample(example) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Msg buildMsg(String clientId, String body) throws BizException {
		RestClientPO clientPO = clientPOMapper.selectByPrimaryKey(clientId);
		if (clientPO == null) {
			throw new BizException(CodeConst.BIZ_ERROR, "clientId不存在");
		}
		String publicKeyStr = clientPO.getPublicKey();
		Msg msg = new Msg();
		try {
			PublicKey publicKey = RsaUtil.loadPublicKey(publicKeyStr);
			msg.setData(Base64Utils.encode(RsaUtil.encryptByPublicKey(body.getBytes(), publicKey)));
			msg.setSign(RsaUtil.signBase64(this.getOwnerPrivateKey(), msg.getData()));
		} catch (BizException e) {
			throw e;
		} catch (Exception e) {
			log.error("buildMsg error", e);
			throw new BizException(CodeConst.BIZ_ERROR, "数据加密失败");
		}
		return msg;
	}

	/**
	 * 获取平台私钥
	 * 
	 * @throws BizException
	 */
	private PrivateKey getOwnerPrivateKey() throws BizException {
		RestClientPO clientPO = clientPOMapper.selectByPrimaryKey(OwnerClientId);
		if (clientPO == null) {
			throw new BizException(CodeConst.BIZ_ERROR, "OwnerClientId不存在");
		}
		try {
			return RsaUtil.loadPrivateKey(clientPO.getPrivateKey());
		} catch (Exception e) {
			throw new BizException(CodeConst.BIZ_ERROR, "加载私钥失败");
		}
	}

	@Override
	public boolean verify(String clientId, String data, String sign) throws BizException {
		RestClientPO clientPO = clientPOMapper.selectByPrimaryKey(clientId);
		if (clientPO == null) {
			throw new BizException(CodeConst.BIZ_ERROR, "ClientId不存在");
		}
		try {
			if (!RsaUtil.verifyBase64(RsaUtil.loadPublicKey(clientPO.getPublicKey()), data, sign)) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			log.error("verifyBase64 error", e);
		}
		return false;
	}

	@Override
	public String decrpt(String clientId, String data) throws BizException {
		PrivateKey privateKey = this.getOwnerPrivateKey();
		try {
			return new String(RsaUtil.decryptByPrivateKey(Base64Utils.decode(data), privateKey));
		} catch (Exception e) {
			log.error("decrpt error", e);
			throw new BizException(CodeConst.BIZ_ERROR, "数据解密失败");
		}

	}

}
