package com.appdirect.integration;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import com.appdirect.integration.configuration.DataSourceContext;

public class AppResource {

	@Context ServletContext app;
	
	public void setDataSource() {
		String datasource = "";
		if(app!=null && app.getAttribute("datasource")!= null) {
			datasource = app.getAttribute("datasource").toString();
		}
		DataSourceContext.setDataSource(datasource);
	}
	
}
