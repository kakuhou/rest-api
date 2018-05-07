package com.kakuhou.sys.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.kakuhou.annotation.Unencrypt;
import com.kakuhou.config.RequestMappingHandlerConfig;
import com.kakuhou.constant.DataSourceConst;
import com.kakuhou.dao.datasource.DataSource;
import com.kakuhou.dao.generator.bean.RestInterfacePO;
import com.kakuhou.dao.generator.bean.RestInterfacePOExample;
import com.kakuhou.dao.generator.bean.RestRightPO;
import com.kakuhou.dao.generator.mapper.RestInterfacePOMapper;
import com.kakuhou.dao.generator.mapper.RestRightPOMapper;
import com.kakuhou.sys.ISysBiz;

/**
 * 系统业务实现
 * 
 * @author guopeng
 */
@Service
public class SysBizImpl implements ISysBiz {
	@Autowired
	private RestRightPOMapper rightPOMapper;
	@Autowired
	private RestInterfacePOMapper interfacePOMapper;
	@Autowired
	RequestMappingHandlerConfig requestMappingHandlerConfig;

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
			patterns.forEach(mapper ->{
				RestInterfacePOExample example = new  RestInterfacePOExample();
				example.createCriteria().andInterfaceUriEqualTo(mapper);
				List<RestInterfacePO> interfacePOs = interfacePOMapper.selectByExample(example);
				if (CollectionUtils.isEmpty(interfacePOs)) {
					RestInterfacePO po = new RestInterfacePO();
					po.setEncrypted(unencrypt == null? true :false);
					po.setInterfaceUri(mapper);
					interfacePOMapper.insertSelective(po);
				}else{
					RestInterfacePO interfacePO = interfacePOs.get(0);
					if (interfacePO.getEncrypted() != (unencrypt == null? true :false)) {
						interfacePO.setEncrypted(unencrypt == null? true :false);
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

}
