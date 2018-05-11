/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: AddClientRq.java 
 * @Prject: rest-api-client
 * @Package: com.kakuhou.rq 
 * @author: guopeng   
 * @date: 2018年5月11日 下午3:25:46 
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
@Url("/api/right/addClient")
public class AddClientRq extends Rq{
	/**
	 * 客户端说明
	 * */
	@NotBlank(message = "description不能为空")
	private String description;
}
