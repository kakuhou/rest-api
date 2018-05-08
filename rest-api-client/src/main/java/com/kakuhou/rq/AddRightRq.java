package com.kakuhou.rq;

import com.kakuhou.base.Rq;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class AddRightRq extends Rq{
	
	/**
	 * 权限描述
	 * */
	private String desc;
}
