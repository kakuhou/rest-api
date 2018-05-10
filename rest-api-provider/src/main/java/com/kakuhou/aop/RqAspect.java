/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: RqAspect.java 
 * @Prject: rest-api-provider
 * @Package: com.kakuhou.aop 
 * @author: guopeng   
 * @date: 2018年5月10日 上午10:32:49 
 * @version: V1.0   
 */
package com.kakuhou.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 请求参数解析
 * @author: guopeng
 * @date: 2018年5月10日
 * @title  请求参数解析
 */
@Slf4j
@Aspect
@Component
@Order(3)
public class RqAspect {
	/**
	 * 所有标注了@RequestMapping标签的方法切入点
	 */
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	private void aspect() {
	}

	@Around("aspect()")
	public Object around(JoinPoint joinPoint) throws Throwable {
		return ((ProceedingJoinPoint) joinPoint).proceed();
	}
}
