package com.kakuhou.utils;

import com.kakuhou.base.Rt;

public class RtUtil {
	/**
	 * 创建返回对象
	 */
	public static <T> Rt<T> create(Integer code, String msg, T data) {
		Rt<T> rt = new Rt<>();
		rt.setCode(code);
		rt.setMsg(msg);
		rt.setData(data);
		return rt;
	}

	/**
	 * 创建成功返回对象
	 */
	public static <T> Rt<T> createSuccess(T data) {
		return create(0, "成功", data);
	}

	/**
	 * 创建成功返回对象
	 */
	public static <T> Rt<T> createSuccess() {
		return create(0, "成功", null);
	}

	/**
	 * 创建失败返回对象
	 */
	public static <T> Rt<T> createError(Integer code, String msg) {
		return create(code, msg, null);
	}

	/**
	 * 创建系统异常返回对象
	 */
	public static <T> Rt<T> createSysError() {
		return create(99, "系统异常", null);
	}
}
