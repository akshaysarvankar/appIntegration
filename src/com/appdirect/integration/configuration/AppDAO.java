package com.appdirect.integration.configuration;

import org.springframework.jdbc.core.PreparedStatementCreator;

public interface AppDAO {
	
	/**
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public PreparedStatementCreator createInsertStatement(Object entity) throws Exception;
	
	/**
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public PreparedStatementCreator createUpdateStatement(Object entity) throws Exception;
	
	/**
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public PreparedStatementCreator createDeleteStatement(Object entity) throws Exception;

}
