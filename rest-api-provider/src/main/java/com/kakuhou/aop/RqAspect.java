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

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.kakuhou.annotation.Unencrypt;
import com.kakuhou.base.Rq;
import com.kakuhou.constant.CodeConst;
import com.kakuhou.exception.BizException;
import com.kakuhou.sys.ISysBiz;
import com.kakuhou.utils.CommonUtil;
import com.kakuhou.utils.HttpServletUtil;
import com.kakuhou.utils.RtUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 请求参数解析
 * 
 * @author: guopeng
 * @date: 2018年5月10日
 * @title 请求参数解析
 */
@Slf4j
@Aspect
@Component
@Order(3)
public class RqAspect {

	private static Gson gson = new Gson();

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
		MethodSignature sig = (MethodSignature) joinPoint.getSignature();
		Method method = sig.getMethod();
		if (!method.isAnnotationPresent(Unencrypt.class)) {
			try {
				HttpServletRequest request = HttpServletUtil.getCurrentRequest();
				String clientId = HttpServletUtil.getClientId();
				String data = request.getParameter("data");
				String sign = request.getParameter("sign");
				if (!sysBiz.verify(clientId, data, sign)) {
					return RtUtil.createError(CodeConst.UNCERTIFIED_ERROR, "数据签名错误");
				}
				String rqStr = sysBiz.decrpt(clientId, data);
				Rq rq = (Rq) CommonUtil.getTargetObject(joinPoint.getArgs(), Rq.class);
				if (rq != null) {
					BeanUtils.copyProperties(gson.fromJson(rqStr, Rq.class), rq);
				}
			} catch (BizException e) {
				return RtUtil.createError(e);
			} catch (Exception e) {
				log.error("RqAspect error", e);
				return RtUtil.createSysError();
			}
		}
		return ((ProceedingJoinPoint) joinPoint).proceed();
	}
}
