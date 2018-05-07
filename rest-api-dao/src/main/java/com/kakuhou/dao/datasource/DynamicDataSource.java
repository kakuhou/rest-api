package com.kakuhou.dao.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicDataSource extends AbstractRoutingDataSource{


	/**
	 * 重写数据源，利用ThreadLocal，每个线程单独搞一份
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		
		return DynamicDataSourceHolder.getDataSouce();
	}

}
