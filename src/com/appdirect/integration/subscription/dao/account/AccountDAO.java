package com.appdirect.integration.subscription.dao.account;

import java.util.List;

import com.appdirect.integration.configuration.AppDAO;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Account;


public interface  AccountDAO extends AppDAO {
	

	/**
	 * @param object
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Account add(Account object, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param object
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Account update(Account object, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param Id
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public boolean remove(Integer Id, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param Id
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Account getObject(Object obj, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param condition
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public List<Account> getObjectByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param condition
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getObjectIdByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception;
	
	
}
