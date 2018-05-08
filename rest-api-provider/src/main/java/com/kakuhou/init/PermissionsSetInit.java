package com.kakuhou.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kakuhou.sys.ISysBiz;

/**
 * 接口集合更新
 * 
 * @author: guopeng
 * @date: 2018年1月29日
 * @title 接口集合更新
 */
@Component
public class PermissionsSetInit implements InitializingBean {
	@Autowired
	ISysBiz sysBiz;

	/**
	 * @Description: 初始化
	 * @throws Exception
	 * @author: guopeng
	 * @date: 2018年5月8日 下午2:21:44
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		sysBiz.initInterface();
	}

}
