package com.appdirect.integration.subscription.dao.creator;

import java.util.List;

import com.appdirect.integration.configuration.AppDAO;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Creator;


public interface  CreatorDAO extends AppDAO {
	

	/**
	 * @param object
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Creator add(Creator object, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param object
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Creator update(Creator object, AppJdbcTemplate jTemplate) throws Exception;
	
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
	public Creator getObject(Object obj, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param condition
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public List<Creator> getObjectByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param condition
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getObjectIdByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception;
	
	
}
