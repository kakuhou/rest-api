package com.kakuhou.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kakuhou.sys.ISysBiz;

/**
 * 消息转换
 * 
 * @author: guopeng
 * @date: 2018年1月29日
 * @title 消息转换
 */
public class MessageFilter extends OncePerRequestFilter {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ISysBiz sysBiz;
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
		String uri = StringUtils.removeStart(request.getRequestURI(), request.getContextPath());
		if (sysBiz.isEncrypted(uri)) {
			filterChain.doFilter(request, response);
		}else{
			MessageHttpServletWrapper wrapper = new MessageHttpServletWrapper(response);
			filterChain.doFilter(request, wrapper);
			String result  = new String(wrapper.getByteArray());
		}
		
	}

}
