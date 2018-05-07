package com.kakuhou.sys;

/**
 * 系统业务
 * */
public interface ISysBiz {
	/**
	 * @param desc 权限描述
	 * */
	void addRight(String desc);
	
	/**
	 *  初始化接口集合
	 * */
	void initInterface();
	/**
	 * 接口是否加密
	 * */
	boolean isEncrypted(String uri);
}
