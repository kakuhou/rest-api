package com.kakuhou.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakuhou.base.Rt;
import com.kakuhou.client.RightClient;
import com.kakuhou.rq.AddRightRq;
import com.kakuhou.sys.ISysBiz;
import com.kakuhou.utils.RtUtil;

/**
 * 系统管理
 */	
@Controller
@RequestMapping(path = "/right")
public class RightController implements RightClient {
	@Autowired
	ISysBiz sysBiz;

	/**
	 * 添加权限组
	 */
	@RequestMapping(path = "/addRight", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Rt<Integer> addRight(AddRightRq rq) throws Exception {
		sysBiz.addRight(rq.getDesc());
		return RtUtil.createSuccess();
	}
}
