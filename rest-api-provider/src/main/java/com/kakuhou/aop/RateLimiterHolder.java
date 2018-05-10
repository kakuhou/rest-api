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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.util.concurrent.RateLimiter;
import com.kakuhou.annotation.Rate;

import lombok.extern.slf4j.Slf4j;

/**
 * RateLimiter装配
 * 
 * @author: guopeng
 * @date: 2018年5月10日
 * @title RateLimiter装配
 */
@Slf4j
public class RateLimiterHolder {
	/**
	 * 过期时间（分钟）
	 */
	private static final long EXPIRE_TIME = 30 * 60 * 1000;

	private static Map<String, RateLimiter> limiters = new ConcurrentHashMap<>();
	private static Map<String, Long> limiterCreateTime = new ConcurrentHashMap<>();
	static {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						long now = System.currentTimeMillis();
						List<String> keys = new ArrayList<>();
						limiterCreateTime.forEach((k, v) -> {
							if (now - v > EXPIRE_TIME) {
								keys.add(k);
							}
						});
						keys.forEach((key) -> {
							limiters.remove(key);
							limiterCreateTime.remove(key);
						});
						Thread.sleep(EXPIRE_TIME);
					} catch (Throwable e) {
						log.error("RateLimiterHolder sleep error", e);
					}
				}
			}
		}).start();
	}

	public static RateLimiter getRateLimiter(String key, Rate rate) {
		if (limiters.containsKey(key)) {
			return limiters.get(key);
		}
		RateLimiter limiter = RateLimiter.create(rate.permitsPerSecond());
		limiters.put(key, limiter);
		limiterCreateTime.put(key, System.currentTimeMillis());
		return limiter;
	}
}
