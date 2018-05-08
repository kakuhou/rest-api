package com.kakuhou.exception;

public class BizException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private Integer code;

	public BizException(Integer code,String msg) {
		super(msg);
		this.code = code;
		
	}
	
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	
}
