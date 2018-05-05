package com.kakuhou.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakuhou.base.Rt;
import com.kakuhou.utils.RtUtil;

/**
 * 系统管理
 */
@Controller
@RequestMapping(value = "/sys")
public class SysController {

	@RequestMapping()
	public Rt<Integer> addRight() {
		return RtUtil.createSuccess();
	}
}
