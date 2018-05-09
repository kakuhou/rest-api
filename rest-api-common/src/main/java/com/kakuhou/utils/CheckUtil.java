package com.kakuhou.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.util.CollectionUtils;

import com.kakuhou.constant.CodeConst;
import com.kakuhou.exception.BizException;

/**
 * 参数校验工具
 * 
 * @author: guopeng
 * @date: 2017年9月21日
 * @title 参数校验工具
 */
public class CheckUtil {
	private static Validator validator;
	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public static void checkRequest(Object request) throws BizException {
		if (request == null) {
			throw new BizException(CodeConst.PARAM_ERROR, "参数不能为空");
		}
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
		if (!CollectionUtils.isEmpty(constraintViolations)) {
			for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
				throw new BizException(CodeConst.PARAM_ERROR, constraintViolation.getMessage());
			}
		}
	}
}
