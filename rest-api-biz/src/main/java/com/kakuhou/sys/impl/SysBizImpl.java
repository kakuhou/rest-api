package com.kakuhou.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addRight(String desc) {
		RestRightPO record = new RestRightPO();
		record.setDesc(desc);
		rightPOMapper.insertSelective(record);
	}

}
