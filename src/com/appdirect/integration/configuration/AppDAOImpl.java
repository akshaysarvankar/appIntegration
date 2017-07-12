package com.appdirect.integration.configuration;

public class AppDAOImpl {

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
