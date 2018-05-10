package com.kakuhou.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.kakuhou.constant.CodeConst;
import com.kakuhou.exception.BizException;
import com.kakuhou.sys.ISysBiz;
import com.kakuhou.utils.HttpServletUtil;
import com.kakuhou.utils.RtUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 消息转换
 * 
 * @author: guopeng
 * @date: 2018年1月29日
 * @title 消息转换
 */
@Slf4j
public class MessageFilter extends OncePerRequestFilter {
	@Autowired
	private ISysBiz sysBiz;

	private Gson gson = new Gson();

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
		String uri = HttpServletUtil.getMappingPath();
		if (!sysBiz.isEncrypted(uri)) {
			filterChain.doFilter(request, response);
		} else {
			String clientId = HttpServletUtil.getClientId();
			if (!sysBiz.isClientLegal(clientId)) {
				writeResponse(response, gson.toJson(RtUtil.createError(CodeConst.UNCERTIFIED_ERROR, "客户端id非法")));
				return;
			}
			MessageHttpServletWrapper wrapper = new MessageHttpServletWrapper(response);
			filterChain.doFilter(request, wrapper);
			String result = new String(wrapper.getByteArray());
			try {
				writeResponse(response, gson.toJson(sysBiz.buildMsg(clientId, result)));
			} catch (BizException e) {
				writeResponse(response, gson.toJson(RtUtil.createError(e)));
			}
		}

	}

	/**
	 * 数据写入
	 */
	private void writeResponse(HttpServletResponse response, String body) {
		try {
			response.getOutputStream().write(body.getBytes());
		} catch (Exception e) {
			log.error("writeResponse error", e);
		}

	}
}
