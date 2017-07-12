package com.appdirect.integration.subscription.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.appdirect.integration.configuration.AppDAO;
import com.appdirect.integration.entities.Response;
import com.appdirect.integration.entities.SubscriptionCancel;
import com.appdirect.integration.entities.SubscriptionChange;
import com.appdirect.integration.entities.SubscriptionOrder;



public interface  SubscriptionDAO extends AppDAO {
	
	/**
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public Response addSubscriptionOrder(SubscriptionOrder order, JdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public Response cancelSubscription(SubscriptionCancel order, JdbcTemplate jTemplate) throws Exception;
	
	/**
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public Response updateSubscriptionChange(SubscriptionChange order, JdbcTemplate jTemplate) throws Exception;
}
