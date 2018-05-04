package com.kakuhou.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 消息转换
 * 
 * @author: guopeng
 * @date: 2018年1月29日
 * @title 消息转换
 */
public class MessageFilter extends OncePerRequestFilter {

	/**
	 * @Description: 过滤处理
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 * @author: guopeng
	 * @date: 2018年1月29日 下午2:39:27
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	}

}
