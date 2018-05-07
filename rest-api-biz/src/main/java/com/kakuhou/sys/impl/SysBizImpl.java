package com.kakuhou.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakuhou.constant.DataSourceConst;
import com.kakuhou.dao.datasource.DataSource;
import com.kakuhou.dao.generator.bean.RestRightPO;
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
	public void initInterface(ContextRefreshedEvent sce) {
		
	}

}
