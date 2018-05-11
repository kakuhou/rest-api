/**   
 * Copyright © 2018 猪八戒. All rights reserved.
 * 
 * @Title: ParamValidateAspect.java 
 * @Prject: java-jinrong-p2p-provider
 * @Package: com.jinrong.p2p.web.aspect 
 * @author: guopeng   
 * @date: 2018年1月29日 下午4:05:22 
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

import com.kakuhou.base.Rq;
import com.kakuhou.exception.BizException;
import com.kakuhou.utils.CheckUtil;
import com.kakuhou.utils.CommonUtil;
import com.kakuhou.utils.RtUtil;

/**
 * 参数校验切面
 * 
 * @author: guopeng
 * @date: 2018年1月29日
 * @title 参数校验切面
 */
@Aspect
@Component
@Order(3)
public class ParamValidateAspect {
	/**
	 * 所有标注了@RequestMapping标签的方法切入点
	 */
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	private void aspect() {
	}

	@Around("aspect()")
	public Object around(JoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		Object form = CommonUtil.getTargetObject(args, Rq.class);
		try {
			CheckUtil.checkRequest(form);
		} catch (BizException e) {
			return RtUtil.createError(e);
		}
		return ((ProceedingJoinPoint) joinPoint).proceed();
	}

}
