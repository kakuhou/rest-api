/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: RateAspect.java 
 * @Prject: rest-api-provider
 * @Package: com.kakuhou.aop 
 * @author: guopeng   
 * @date: 2018年5月10日 下午2:55:48 
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
 * 限流切面
 * @author: guopeng
 * @date: 2018年5月10日
 * @title  限流切面
 */
@Slf4j
@Aspect
@Component
@Order(3)
public class RateAspect {
	/**
	 * 所有标注了@Rate标签的方法切入点
	 */
	@Pointcut("@annotation(com.kakuhou.annotation.Rate)")
	private void aspect() {
	}

	@Around("aspect()")
	public Object around(JoinPoint joinPoint) throws Throwable {
		return ((ProceedingJoinPoint) joinPoint).proceed();
	}
}
