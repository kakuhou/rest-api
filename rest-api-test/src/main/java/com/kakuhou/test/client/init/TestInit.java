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

import com.google.gson.Gson;
import com.kakuhou.client.ClientFactory;

/**
 * 测试类初始化
 * 
 * @author: guopeng
 * @date: 2018年5月11日
 * @title 测试类初始化
 */
public class TestInit {
	public Gson gson = new Gson();
	
	static {
		ClientFactory.init("http://127.0.0.1:8001", "4edbc80e54f211e8b2f28cd68a31561c",
				"MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJoX5WPyF+fFyJ6JcazHcE6Pfb6kN4Hg8NfphMDmcK1n+t7uJupOkVD6JpsWXCkJJp9eorXl+gViFiL10TrKqzAmCzqjIGLMw1N0joAkKWQ9noGiRooxP9yfRUe4vOjJU56zLfrC9i5yE646yZMAMm+BTPBWiLkNZeZcg9w+s8vrAgMBAAECgYA2oZA3TgaBqpKE+o+txQuhHqhjJY90YY6hze2+AjUilYHQ9bggS/ijL/zt8RB9j/v8dq3TNZTdwbe9b09du6rTGZJwazC3/XwmLOwMRoQyAmvZupZsISx9A4tUlqkjOKLNByStfj6fCn5G5VjpE/19/v+3HrZDkqIW+JswNt/uWQJBAOk5OGDIqgykGAmbXvN2LS3HZQ1tSwLv3DChzNlC3/kQygxK2dQ8+5E8F8fd8q9/CMz9fOwuaOfu7CEmPRFQz20CQQCpJFs+VKVNPLsMQoAJaovnVEMKOzLeDTWXzvzwW+yFq343p3W8421Tp+BF0L+9QOSIrsxQWK3mAW6pNkdrWXm3AkACLnPVi+rzRVGoPVjNBCz2AahzULNj6qRvnAPTgGmh5cNODCEveAaOXDaIuw27EIAfkrtpZiG7EaQJx1bI462dAkBhTb8H/p4R7uwUQ3Vw9VCCxiDDFAdRYvv/113/0IIi6+NylO3Qum08gQkuYMbNUzWlxBlZ54ozFYPZ6JXxlcnFAkAsfb3T/3LqrOr7A2yyLndAgwvqsewkrRm9lhnEgJxFMxsLK+CnbT3FCk+CshqByHAaG6oEdmGHMl6R6Dig21bu",
				"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaF+Vj8hfnxcieiXGsx3BOj32+pDeB4PDX6YTA5nCtZ/re7ibqTpFQ+iabFlwpCSafXqK15foFYhYi9dE6yqswJgs6oyBizMNTdI6AJClkPZ6BokaKMT/cn0VHuLzoyVOesy36wvYuchOuOsmTADJvgUzwVoi5DWXmXIPcPrPL6wIDAQAB");
	}
}
