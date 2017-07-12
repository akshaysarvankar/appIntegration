package com.appdirect.integration.configuration;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class AppDataSource extends AbstractRoutingDataSource implements DisposableBean{

	@Override
	public void destroy() throws Exception {
		try {
			this.getConnection().close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContext.getDataSourceName();
	}

}
