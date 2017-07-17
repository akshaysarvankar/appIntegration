package com.appdirect.integration.subscription.dao;

import com.appdirect.integration.configuration.AppDAOManager;
import com.appdirect.integration.entities.Response;
import com.appdirect.integration.entities.userevent.UserAssignment;
import com.appdirect.integration.entities.userevent.UserUnassignment;
import com.appdirect.integration.entities.userevent.UserUpdated;


public interface  UserEventManager extends AppDAOManager {
	
	/**
	 * @param userEvent
	 * @return
	 * @throws Exception
	 */
	public Response userAssignment(UserAssignment userEvent) throws Exception ;
	
	/**
	 * @param userEvent
	 * @return
	 * @throws Exception
	 */
	public Response userUnassignment(UserUnassignment userEvent) throws Exception;
	
	/**
	 * @param userEvent
	 * @return
	 * @throws Exception
	 */
	public Response userUpdate(UserUpdated userEvent) throws Exception;


}
