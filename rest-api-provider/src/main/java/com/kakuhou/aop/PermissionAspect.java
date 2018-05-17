/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: PermissionAspect.java 
 * @Prject: rest-api-provider
 * @Package: com.kakuhou.aop 
 * @author: guopeng   
 * @date: 2018年5月17日 下午4:10:41 
 * @version: V1.0   
 */
package com.kakuhou.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.kakuhou.constant.CodeConst;
import com.kakuhou.sys.ISysBiz;
import com.kakuhou.utils.HttpServletUtil;
import com.kakuhou.utils.RtUtil;

/**
 * 权限控制切面
 * @author: guopeng
 * @date: 2018年5月17日
 * @title  权限控制切面
 */
@Aspect
@Component
@Order(2)
public class PermissionAspect {
	@Autowired
	ISysBiz sysBiz;
	/**
	 * 所有标注了@RequestMapping标签的方法切入点
	 */
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	private void aspect() {
	}

	@Around("aspect()")
	public Object around(JoinPoint joinPoint) throws Throwable {
		String uri = HttpServletUtil.getMappingPath();
		String clientId = HttpServletUtil.getClientId();
		if (!sysBiz.hasPermission(clientId, uri)) {
			return RtUtil.createError(CodeConst.BIZ_ERROR, "无权限访问");
		}
		return ((ProceedingJoinPoint) joinPoint).proceed();
	}
}
