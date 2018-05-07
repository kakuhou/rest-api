package com.kakuhou.dao.datasource;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class DynamicDataSourceHolder {

	
	/**
	 * 开启本地线程，防止线程不安全， 本来spring的线程就不安全
	 */
	public static final ThreadLocal<String> holder = new ThreadLocal();

	
	
    public DynamicDataSourceHolder() {
    }

    /**
     * 
     * @Title: setDataSource 
     * @Description: 重新开启新的数据源
     * @param name
     * @return: void
     */
    public static void setDataSource(String name) {
        holder.set(name);
    }

    /**
     * 
     * @Title: getDataSouce 
     * @Description: 默认方法，只重新插入，不重新拿取
     * @return
     * @return: String
     */
    public static String getDataSouce() {
        return (String)holder.get();
    }
    
    /**
     * 
     * @Title: clearCustomerType 
     * @Description: 删除本线程的数据源
     * @return: void
     */
    public static void clearCustomerType() {  

    	holder.remove();  

    }  
}
