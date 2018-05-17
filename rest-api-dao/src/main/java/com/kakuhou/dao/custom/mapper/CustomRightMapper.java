/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: CustomRightMapper.java 
 * @Prject: rest-api-dao
 * @Package: com.kakuhou.dao.custom 
 * @author: guopeng   
 * @date: 2018年5月17日 下午4:29:18 
 * @version: V1.0   
 */
package com.kakuhou.dao.custom.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 权限
 * @author: guopeng
 * @date: 2018年5月17日
 * @title  权限
 */
public interface CustomRightMapper {
	
	/**
	 * 是否有权限
	 * */
	Integer hasPermission(@Param("clientId") String clientId, @Param("uri")String uri);
}
