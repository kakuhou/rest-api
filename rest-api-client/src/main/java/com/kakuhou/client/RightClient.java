package com.kakuhou.client;

import com.kakuhou.base.Rt;
import com.kakuhou.rq.AddRightRq;

public interface RightClient {
	/**
	 * 添加权限
	 * 
	 * @param rq
	 * @return Rt<Integer>
	 */
	public Rt<Integer> addRight(AddRightRq rq) throws Exception;

}
