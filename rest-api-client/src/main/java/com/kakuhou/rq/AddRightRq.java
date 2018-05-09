package com.kakuhou.rq;

import com.kakuhou.base.Rq;
import com.kakuhou.base.Url;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Url("/api/right/addRight")
public class AddRightRq extends Rq {

	/**
	 * 权限描述
	 */
	private String desc;
}
