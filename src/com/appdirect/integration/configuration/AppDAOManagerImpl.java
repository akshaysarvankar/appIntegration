package com.appdirect.integration.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOManagerImpl implements AppDAOManager {

	
	@Autowired
	protected AppJdbcTemplate jTemplate= null;
	
	public AppDAOManagerImpl() {
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(jTemplate != null) {
			jTemplate.setMaxRows(1000);;
		}
		
	}

	@Override
	public void destroy() throws Exception {
		DataSourceContext.clearDataSourceName();
		jTemplate.destroy();
		jTemplate = null;
		
	}

	@Override
	public void setJdbcTemplate(AppJdbcTemplate template) {
		this.jTemplate = template;
		
	}
	
	public void assertNull(String message,Object obj) throws Exception{
		if(obj == null) {
			throw new Exception(message);
		}
	}
	
	public void assertTrue(String message,boolean condition) throws Exception{
		if(condition) {
			throw new Exception(message);
		}
	}
	
	public void assertAreEqual(String message,Object obj1, Object obj2) throws Exception{
		if(!obj1.equals(obj2)) {
			throw new Exception(message);
		}
	}

}
