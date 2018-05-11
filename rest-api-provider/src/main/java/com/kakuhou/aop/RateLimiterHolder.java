/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: RateLimiterHolder.java 
 * @Prject: rest-api-provider
 * @Package: com.kakuhou.aop 
 * @author: guopeng   
 * @date: 2018年5月10日 下午5:27:35 
 * @version: V1.0   
 */
package com.kakuhou.aop;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.util.concurrent.RateLimiter;
import com.kakuhou.annotation.Rate;

/**
 * RateLimiter装配
 * 
 * @author: guopeng
 * @date: 2018年5月10日
 * @title RateLimiter装配
 */
public class RateLimiterHolder {

	private static Map<String, RateLimiter> limiters = new ConcurrentHashMap<>();

	public static RateLimiter getRateLimiter(String key, Rate rate) {
		if (limiters.containsKey(key)) {
			return limiters.get(key);
		}
		RateLimiter limiter = RateLimiter.create(rate.permitsPerSecond());
		limiters.put(key, limiter);
		return limiter;
	}
}
