package com.kakuhou.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * HttpServlet工具
 * 
 * @author: guopeng
 * @date: 2018年5月10日
 * @title HttpServlet工具
 */
public class HttpServletUtil {
	/**
	 * 获取当前线程HttpServlet请求对象
	 */
	public static HttpServletRequest getCurrentRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 获取当前请求的clientId
	 */
	public static String getClientId() {
		return getCurrentRequest().getHeader("clientId");
	}

	public static String getMappingPath() {
		HttpServletRequest request = getCurrentRequest();
		return StringUtils.removeStart(request.getRequestURI(), request.getContextPath());
	}
}
