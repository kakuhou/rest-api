package com.kakuhou.base;

import lombok.Data;

/**
 * 消息体
 * */
@Data
public class Msg {
	/**
	 * 加密数据
	 * */
	private String data;
	/**
	 * 数据签名
	 * */
	private String sign;
	
}
