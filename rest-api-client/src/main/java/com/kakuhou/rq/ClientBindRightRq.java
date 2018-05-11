/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: ClientBindRightRq.java 
 * @Prject: rest-api-client
 * @Package: com.kakuhou.rq 
 * @author: guopeng   
 * @date: 2018年5月11日 下午3:26:39 
 * @version: V1.0   
 */
package com.kakuhou.rq;

import org.hibernate.validator.constraints.NotBlank;

import com.kakuhou.base.Rq;
import com.kakuhou.base.Url;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: guopeng
 * @date: 2018年5月11日
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Url("/api/right/clientBindRight")
public class ClientBindRightRq extends Rq {
	/**
	 * 客户端id
	 */
	@NotBlank(message = "tagClientId不能为空")
	private String tagClientId;
	/**
	 * 权限id
	 */
	@NotBlank(message = "rightId不能为空")
	private String rightId;
}
