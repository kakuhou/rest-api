package com.kakuhou.sys.impl;

import java.security.KeyPair;
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
import com.kakuhou.dao.generator.bean.RestClientRightPO;
import com.kakuhou.dao.generator.bean.RestInterfacePO;
import com.kakuhou.dao.generator.bean.RestInterfacePOExample;
import com.kakuhou.dao.generator.bean.RestRightInterfacePO;
import com.kakuhou.dao.generator.bean.RestRightPO;
import com.kakuhou.dao.generator.mapper.RestClientPOMapper;
import com.kakuhou.dao.generator.mapper.RestClientRightPOMapper;
import com.kakuhou.dao.generator.mapper.RestInterfacePOMapper;
import com.kakuhou.dao.generator.mapper.RestRightInterfacePOMapper;
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
	private RestRightInterfacePOMapper rightInterfacePOMapper;
	@Autowired
	private RestClientRightPOMapper clientRightPOMapper;
	@Autowired
	RequestMappingHandlerConfig requestMappingHandlerConfig;
	@Value("${Owner.ClientId}")
	private String OwnerClientId;

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
	public void delRight(String id) throws BizException {
		RestRightPO rightPO = rightPOMapper.selectByPrimaryKey(id);
		if (rightPO == null) {
			throw new BizException(CodeConst.BIZ_ERROR, "权限id不存在");
		}
		rightPOMapper.deleteByPrimaryKey(id);
	}

	@DataSource(DataSourceConst.DATASOURCE_MASTER)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateRight(String id, String desc) throws BizException {
		RestRightPO rightPO = rightPOMapper.selectByPrimaryKey(id);
		if (rightPO == null) {
			throw new BizException(CodeConst.BIZ_ERROR, "权限id不存在");
		}
		rightPO.setDescription(desc);
		rightPO.setUpdateTime(new Date());
		rightPOMapper.updateByPrimaryKeySelective(rightPO);
	}

	@DataSource(DataSourceConst.DATASOURCE_MASTER)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void bindInterfaceToRight(List<String> interfaceIds, String rightId) {
		if (!CollectionUtils.isEmpty(interfaceIds)) {
			interfaceIds.forEach(id -> {
				RestRightInterfacePO interfacePO = new RestRightInterfacePO();
				interfacePO.setInterfaceId(id);
				interfacePO.setRightId(rightId);
				rightInterfacePOMapper.insertSelective(interfacePO);
			});
		}
	}

	@DataSource(DataSourceConst.DATASOURCE_MASTER)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addClient(String description) {
		RestClientPO record = new RestClientPO();
		record.setDescription(description);
		KeyPair keyPair = RsaUtil.generateRSAKeyPair();
		record.setPrivateKey(Base64Utils.encode(keyPair.getPrivate().getEncoded()));
		record.setPublicKey(Base64Utils.encode(keyPair.getPublic().getEncoded()));
		clientPOMapper.insertSelective(record);
	}

	@DataSource(DataSourceConst.DATASOURCE_MASTER)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delClient(String id) throws BizException {
		if (clientPOMapper.selectByPrimaryKey(id) == null) {
			throw new BizException(CodeConst.BIZ_ERROR, "客户端id不存在");
		}
		clientPOMapper.deleteByPrimaryKey(id);
	}

	@DataSource(DataSourceConst.DATASOURCE_MASTER)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateClient(String id, String description) throws BizException {
		RestClientPO clientPO = clientPOMapper.selectByPrimaryKey(id);
		if (clientPO == null) {
			throw new BizException(CodeConst.BIZ_ERROR, "客户端id不存在");
		}
		clientPO.setDescription(description);
		clientPO.setUpdateTime(new Date());
		clientPOMapper.updateByPrimaryKeySelective(clientPO);
	}

	@DataSource(DataSourceConst.DATASOURCE_MASTER)
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void clientBindRight(String clientId, String rightId) throws BizException {
		RestClientPO clientPO = clientPOMapper.selectByPrimaryKey(clientId);
		if (clientPO == null) {
			throw new BizException(CodeConst.BIZ_ERROR, "客户端id不存在");
		}
		RestRightPO rightPO = rightPOMapper.selectByPrimaryKey(rightId);
		if (rightPO == null) {
			throw new BizException(CodeConst.BIZ_ERROR, "权限id不存在");
		}
		RestClientRightPO record = new RestClientRightPO();
		record.setClientId(clientId);
		record.setRightId(rightId);
		clientRightPOMapper.insertSelective(record);
	}

}
