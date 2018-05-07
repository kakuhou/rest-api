/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: Unencrypt.java 
 * @Prject: rest-api-common
 * @Package: com.kakuhou.annotation 
 * @author: guopeng   
 * @date: 2018年5月7日 下午2:04:38 
 * @version: V1.0   
 */
package com.kakuhou.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不加密
 * @author: guopeng
 * @date: 2018年5月7日
 * @title  不加密
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unencrypt {

}
