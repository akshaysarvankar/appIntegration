package com.appdirect.integration.subscription.dao;

import com.appdirect.integration.configuration.AppDAOManager;
import com.appdirect.integration.entities.Response;
import com.appdirect.integration.entities.SubscriptionCancel;
import com.appdirect.integration.entities.SubscriptionChange;
import com.appdirect.integration.entities.SubscriptionOrder;


public interface  SubscriptionManager extends AppDAOManager {
	
	/**
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public Response addSubscriptionOrder(SubscriptionOrder order) throws Exception ;
	
	/**
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public Response cancelSubscription(SubscriptionCancel order) throws Exception;
	
	/**
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public Response updateSubscriptionChange(SubscriptionChange order) throws Exception;
	
	/**Send First parameter in Object as class expected in return
	 * @param objects
	 * @return
	 * @throws Exception
	 */
	public SubscriptionOrder getSubscriptionByOrderId(Object...objects) throws Exception;
	

}
