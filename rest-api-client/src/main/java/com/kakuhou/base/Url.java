/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: Url.java 
 * @Prject: rest-api-client
 * @Package: com.kakuhou.base 
 * @author: guopeng   
 * @date: 2018年5月9日 上午9:21:32 
 * @version: V1.0   
 */
package com.kakuhou.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 地址
 * @author: guopeng
 * @date: 2018年5月9日
 * @title  地址
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Url {
	String value();
}
