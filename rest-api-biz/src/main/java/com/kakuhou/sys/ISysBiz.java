package com.kakuhou.sys;

import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 系统业务
 * */
public interface ISysBiz {
	/**
	 * @param desc 权限描述
	 * */
	void addRight(String desc);
	
	/**
	 * @param sce 初始化接口集合
	 * */
	void initInterface(ContextRefreshedEvent sce);
}
