/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: BindInterfaceToRightRq.java 
 * @Prject: rest-api-client
 * @Package: com.kakuhou.rq 
 * @author: guopeng   
 * @date: 2018年5月11日 下午3:24:07 
 * @version: V1.0   
 */
package com.kakuhou.rq;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Url("/api/right/bindInterfaceToRight")
public class BindInterfaceToRightRq extends Rq {
	/**
	 * 接口ids
	 */
	@Size(min = 1, message = "interfaceIds不能为空")
	@NotNull(message = "interfaceIds不能为空")
	private List<String> interfaceIds;
	/**
	 * 权限id
	 */
	@NotBlank(message = "rightId不能为空")
	private String rightId;
}
