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

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;
import com.kakuhou.annotation.LimitScope;
import com.kakuhou.annotation.Rate;
import com.kakuhou.constant.CodeConst;
import com.kakuhou.utils.HttpServletUtil;
import com.kakuhou.utils.RtUtil;

/**
 * 限流切面
 * 
 * @author: guopeng
 * @date: 2018年5月10日
 * @title 限流切面
 */
@Aspect
@Component
@Order(1)
public class RateAspect {

	/**
	 * 所有标注了@Rate标签的方法切入点
	 */
	@Pointcut("@annotation(com.kakuhou.annotation.Rate)")
	private void aspect() {
	}

	@Around("aspect()")
	public Object around(JoinPoint joinPoint) throws Throwable {
		String url = HttpServletUtil.getMappingPath();
		MethodSignature sig = (MethodSignature) joinPoint.getSignature();
		Method method = sig.getMethod();
		Rate rate = method.getAnnotation(Rate.class);
		RateLimiter limiter = getRateLimiter(url, rate);
		if (!limiter.tryAcquire()) {
			return RtUtil.createError(CodeConst.BIZ_ERROR, "接口频率限制");
		}
		return ((ProceedingJoinPoint) joinPoint).proceed();
	}

	private RateLimiter getRateLimiter(String url, Rate rate) {
		LimitScope scope = rate.scope();
		if (scope.equals(LimitScope.ALL)) {
			return RateLimiterHolder.getRateLimiter(url, rate);
		} else {
			return RateLimiterHolder.getRateLimiter(HttpServletUtil.getClientId() + url, rate);
		}
	}
}
