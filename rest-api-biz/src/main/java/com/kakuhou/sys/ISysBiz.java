package com.kakuhou.sys;

import java.util.List;

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
	 * 删除权限
	 */
	void delRight(String id) throws BizException;

	/**
	 * 更新权限
	 */
	void updateRight(String id, String desc) throws BizException;

	/**
	 * 给权限绑定接口
	 */
	void bindInterfaceToRight(List<String> interfaceIds, String rightId);

	/**
	 * 添加客户端
	 */
	void addClient(String description);

	/**
	 * 删除客户端
	 */
	void delClient(String id) throws BizException;

	/**
	 * 更新客户端
	 */
	void updateClient(String id, String description) throws BizException;

	/**
	 * 客户端授权
	 */
	void clientBindRight(String clientId, String rightId) throws BizException;

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

	/**
	 * 客户端数据验签
	 */
	boolean verify(String clientId, String data, String sign) throws BizException;

	/**
	 * 客户端数据解密
	 */
	String decrpt(String clientId, String data) throws BizException;
}
