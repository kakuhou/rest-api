package com.kakuhou.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakuhou.base.Rt;
import com.kakuhou.client.RightClient;
import com.kakuhou.rq.AddClientRq;
import com.kakuhou.rq.AddRightRq;
import com.kakuhou.rq.BindInterfaceToRightRq;
import com.kakuhou.rq.ClientBindRightRq;
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

	/**
	 * @Description: 给权限绑定接口
	 * @param rq
	 * @return
	 * @throws Exception 
	 * @author: guopeng
	 * @date: 2018年5月11日 下午3:27:56 
	 */
	@RequestMapping(path = "/bindInterfaceToRight", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Rt<Integer> bindInterfaceToRight(BindInterfaceToRightRq rq) throws Exception {
		sysBiz.bindInterfaceToRight(rq.getInterfaceIds(), rq.getRightId());
		return RtUtil.createSuccess();
	}

	/**
	 * @Description: 添加客户端
	 * @param rq
	 * @return
	 * @throws Exception 
	 * @author: guopeng
	 * @date: 2018年5月11日 下午3:27:56 
	 */
	@RequestMapping(path = "/addClient", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Rt<Integer> addClient(AddClientRq rq) throws Exception {
		sysBiz.addClient(rq.getDescription());
		return RtUtil.createSuccess();
	}

	/**
	 * @Description: 客户端授权
	 * @param rq
	 * @return
	 * @throws Exception 
	 * @author: guopeng
	 * @date: 2018年5月11日 下午3:27:56 
	 */
	@RequestMapping(path = "/clientBindRight", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public Rt<Integer> clientBindRight(ClientBindRightRq rq) throws Exception {
		sysBiz.clientBindRight(rq.getTagClientId(), rq.getRightId());
		return RtUtil.createSuccess();
	}
}
