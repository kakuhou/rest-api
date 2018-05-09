package com.kakuhou.aop;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakuhou.base.Rt;
import com.kakuhou.exception.BizException;
import com.kakuhou.utils.RtUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author guopeng
 */
@ControllerAdvice
@Order(-1)
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 拦截业务异常
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public <T> Rt<T> exc(BizException e) {
    	return RtUtil.createError(e);
    }
    /**
     * 拦截系统异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> Rt<T> exc(Exception e) {
    	log.error("Exception error", e);
    	return RtUtil.createSysError();
    }
}