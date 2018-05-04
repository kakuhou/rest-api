package org.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedColumn;

/**
 * 不生成withblobs
 * 
 * @author hujianjun
 * @date 2016年4月15日19:58:28
 * 
 */
public class MyIntrospectedColumnImpl extends IntrospectedColumn {

	/**
	 * 不生成withblobs
	 */
	@Override
	public boolean isBLOBColumn() {
		return false;
	}
}
