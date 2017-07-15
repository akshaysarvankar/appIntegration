package com.appdirect.integration.configuration;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class AppDAOImpl {

	public static void assertNull(String message,Object obj) throws Exception{
		if(obj == null) {
			throw new Exception(message);
		}
	}
	
	public static void assertTrue(String message,boolean condition) throws Exception{
		if(condition) {
			throw new Exception(message);
		}
	}
	
	public static void assertAreEqual(String message,Object obj1, Object obj2) throws Exception{
		if(!obj1.equals(obj2)) {
			throw new Exception(message);
		}
	}
	
	public static Object insert(AppDAO dao, Object obj, AppJdbcTemplate jTemplate) throws Exception {
		try {
		assertNull("Object to be added is null", obj);
		PreparedStatementCreator insert = dao.createInsertStatement(obj);
		assertNull("Insert Statment is null", insert);
		return jTemplate.update(insert)== 1? obj : new Exception("Insert failed");
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static KeyHolder insertForPrimaryKey(AppDAO dao, Object obj, AppJdbcTemplate jTemplate) throws Exception {
		try {
		assertNull("Object to be added is null", obj);
		PreparedStatementCreator insert = dao.createInsertStatement(obj);
		assertNull("Insert Statment is null", insert);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jTemplate.update(insert, keyHolder);
		return keyHolder;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static Object update(AppDAO dao, Object obj, AppJdbcTemplate jTemplate) throws Exception {
		try {
		assertNull("Object to be updated is null", obj);
		PreparedStatementCreator update = dao.createUpdateStatement(obj);
		assertNull("update Statment is null", update);
		return jTemplate.update(update)>= 1? obj : new Exception("update failed");
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
