package com.kakuhou.client;

import com.kakuhou.base.Rt;
import com.kakuhou.rq.AddClientRq;
import com.kakuhou.rq.AddRightRq;
import com.kakuhou.rq.BindInterfaceToRightRq;
import com.kakuhou.rq.ClientBindRightRq;

public interface RightClient extends Client {
	/**
	 * 添加权限
	 * 
	 * @param rq
	 * @return Rt<Integer>
	 */
	public Rt<Integer> addRight(AddRightRq rq) throws Exception;

	/**
	 * 给权限绑定接口
	 */
	public Rt<Integer> bindInterfaceToRight(BindInterfaceToRightRq rq) throws Exception;
	
	/**
	 * 添加客户端
	 */
	public Rt<Integer> addClient(AddClientRq rq)throws Exception;
	
	/**
	 * 客户端授权
	 */
	public Rt<Integer> clientBindRight(ClientBindRightRq rq) throws Exception;
}
