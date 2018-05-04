/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: Rt.java 
 * @Prject: rest-api-client
 * @Package: com.kakuhou.base 
 * @author: guopeng   
 * @date: 2018年5月4日 下午3:38:27 
 * @version: V1.0   
 */
package com.kakuhou.base;

/**
 * 返回结果
 * 
 * @author: guopeng
 * @date: 2018年5月4日
 * @title 返回结果
 */
public class Rt<T> {
	/**
	 * 返回码
	 * */
	private Integer code;
	/**
	 * 消息
	 * */
	private String msg;
	/**
	 * 数据
	 * */
	private T data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
