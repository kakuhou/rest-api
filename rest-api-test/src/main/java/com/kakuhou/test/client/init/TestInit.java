/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: TestInit.java 
 * @Prject: rest-api-test
 * @Package: com.kakuhou.test.client.init 
 * @author: guopeng   
 * @date: 2018年5月11日 下午1:53:10 
 * @version: V1.0   
 */
package com.kakuhou.test.client.init;

import com.kakuhou.client.ClientFactory;

/**
 * 测试类初始化
 * @author: guopeng
 * @date: 2018年5月11日
 * @title  测试类初始化
 */
public class TestInit {
	
	static{
		ClientFactory.init("http://127.0.0.1:8001", "", "", "");
	}
}
