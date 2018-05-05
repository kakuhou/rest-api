package com.kakuhou.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 接口集合更新
 * 
 * @author: guopeng
 * @date: 2018年1月29日
 * @title 接口集合更新
 */
@Component
public class PermissionsSetListener implements ApplicationListener<ContextRefreshedEvent> {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * @Description: 处理
	 * @param arg0 
	 * @author: guopeng
	 * @date: 2018年1月30日 上午9:00:12 
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent sce) {
	}

}
