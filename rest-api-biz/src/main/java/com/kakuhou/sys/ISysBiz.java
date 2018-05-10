package com.kakuhou.sys;

import com.kakuhou.base.Msg;
import com.kakuhou.exception.BizException;

/**
 * 系统业务
 */
public interface ISysBiz {
	/**
	 * @param desc
	 *            权限描述
	 */
	void addRight(String desc);

	/**
	 * 初始化接口集合
	 */
	void initInterface();

	/**
	 * 接口是否加密
	 * 
	 * @return 是否加密
	 */
	boolean isEncrypted(String uri);

	/**
	 * 客户端是否合法
	 * 
	 * @param clientId
	 *            客户端id
	 * @return 是否合法
	 */
	boolean isClientLegal(String clientId);


	/**
	 * 构建消息返回体
	 * 
	 * @param clientId
	 *            客户端id
	 * @param body
	 *            未加密数据
	 * @return Msg消息对象
	 */
	Msg buildMsg(String clientId, String body) throws BizException;
}
