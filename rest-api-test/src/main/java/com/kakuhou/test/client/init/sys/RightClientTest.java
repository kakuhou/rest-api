/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: RightClientTest.java 
 * @Prject: rest-api-test
 * @Package: com.kakuhou.test.client.init.sys 
 * @author: guopeng   
 * @date: 2018年5月11日 下午1:55:15 
 * @version: V1.0   
 */
package com.kakuhou.test.client.init.sys;

import org.junit.Test;

import com.kakuhou.client.ClientFactory;
import com.kakuhou.client.RightClient;
import com.kakuhou.rq.AddRightRq;
import com.kakuhou.test.client.init.TestInit;

/**
 * 权限测试
 * @author: guopeng
 * @date: 2018年5月11日
 * @title  权限测试
 */
public class RightClientTest extends TestInit{
	private RightClient  client = ClientFactory.getRightClient(); 
	@Test
	public void addRight() throws Exception{
		AddRightRq rq = new AddRightRq();
		rq.setDesc("aaaa");
		client.addRight(rq);
	}
}
