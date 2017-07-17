package com.appdirect.integration.subscription.dao.event;

import java.util.List;

import com.appdirect.integration.configuration.AppDAO;
import com.appdirect.integration.configuration.AppJdbcTemplate;
import com.appdirect.integration.entities.Event;


public interface  EventDAO extends AppDAO {
	

	/**
	 * @param object
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Event add(Event object, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param object
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public Event update(Event object, AppJdbcTemplate jTemplate) throws Exception;
	
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
	public Event getEventByEventId(Integer EventId, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param accountId
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public List<Event> getEventsByUserId(Object userId, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param condition
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public List<Event> getObjectByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param condition
	 * @param jTemplate
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getObjectIdByCondition(String condition, AppJdbcTemplate jTemplate) throws Exception;
	
	
}
