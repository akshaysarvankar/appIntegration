package com.appdirect.integration.subscription.dao.address;

import java.util.List;

import com.appdirect.integration.configuration.AppDAO;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Address;


public interface  AddressDAO extends AppDAO {
	

	/**
	 * @param object
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Address add(Address object, Class<?> parentClass,AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param object
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Address update(Address object,Class<?> parentClass, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param Id
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public boolean remove(Integer Id,Class<?> parentClass, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param Id
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Address getObject(Object obj, Class<?> parentClass, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param condition
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public List<Address> getObjectByCondition(String condition, Class<?> parentClass, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param condition
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getObjectIdByCondition(String condition,Class<?> parentClass, AppJdbcTemplate jTemplate) throws Exception;
	
	
}
