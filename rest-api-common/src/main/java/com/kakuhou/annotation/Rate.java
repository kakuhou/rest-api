/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: Rate.java 
 * @Prject: rest-api-common
 * @Package: com.kakuhou.annotation 
 * @author: guopeng   
 * @date: 2018年5月7日 下午2:42:44 
 * @version: V1.0   
 */
package com.kakuhou.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口频率
 * @author: guopeng
 * @date: 2018年2月24日
 * @title  接口频率
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Rate {
	/**
	 * 时间间隔 单位毫秒
	 * */
	long interval();
}