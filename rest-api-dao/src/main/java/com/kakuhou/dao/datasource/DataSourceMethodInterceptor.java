package com.kakuhou.dao.datasource;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class DataSourceMethodInterceptor {

	private ConcurrentHashMap<String, String> pointMap = new ConcurrentHashMap<String, String>();

	public void before(JoinPoint point) {
		MethodSignature sig = (MethodSignature) point.getSignature();
		Class<?> clazz = sig.getDeclaringType();
		String name = clazz.getName() + "." + sig.getName();
		Method method = sig.getMethod();

		String sourceName = this.pointMap.get(name);
		if (sourceName == null) {
			DataSource ann = method.getAnnotation(DataSource.class);
			if (ann != null) {
				sourceName = ann.value();
			}
			else {
				sourceName = "";
			}
			this.pointMap.put(name, sourceName);
		}

		if (sourceName.length() > 0) {
			DynamicDataSourceHolder.setDataSource(sourceName);
		}
	}

	public Object doRound(ProceedingJoinPoint joinPoint) throws Throwable {
		// 执行到本方法之前方法的数据源，可能为空
		String preDataSource = DynamicDataSourceHolder.getDataSouce();
		MethodSignature sig = (MethodSignature) joinPoint.getSignature();
		Class<?> clazz = sig.getDeclaringType();
		String name = clazz.getName() + "." + sig.getName();
		Method method = sig.getMethod();

		String sourceName = this.pointMap.get(name);
		if (sourceName == null) {
			DataSource ann = method.getAnnotation(DataSource.class);
			if (ann != null) {
				sourceName = ann.value();
			}
			else {
				sourceName = "";
			}
			this.pointMap.put(name, sourceName);
		}

		if (sourceName.length() > 0) {
			DynamicDataSourceHolder.setDataSource(sourceName);
		}
		try {
			Object result = joinPoint.proceed(joinPoint.getArgs());
			return result;
		}
		finally {
			// 重置数据源
			DynamicDataSourceHolder.setDataSource(preDataSource);
		}
	}


}
