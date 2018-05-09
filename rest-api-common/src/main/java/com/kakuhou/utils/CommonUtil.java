package com.kakuhou.utils;

/**
 * 公共工具类
 * @author: guopeng
 * @date: 2018年5月9日
 * @title  公共工具类
 */
public class CommonUtil {
	/**
	 * @Description: 获取参数中目标对象
	 * @param objects
	 * @param clas
	 * @return
	 * @return: Object
	 * @author: guopeng
	 */
	public static Object getTargetObject(Object[] objects, Class<?> clas) {
		if (objects != null && objects.length > 0) {
			for (Object object2 : objects) {
				if (object2 != null && clas.isAssignableFrom(object2.getClass())) {
					return object2;
				}
			}
		}
		return null;
	}
}
