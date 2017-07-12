package com.appdirect.integration.configuration;
public class DataSourceContext {
	
	private static final ThreadLocal<String> contextHolder= new ThreadLocal<String>();

	public static String getDataSourceName() {
		return contextHolder.get();
	}
	
	public static void setDataSource(String dataSourceName) {
		contextHolder.set(dataSourceName);
	}
	
	public static void clearDataSourceName() {
		contextHolder.remove();
	}
}
