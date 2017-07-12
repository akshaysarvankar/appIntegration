package com.appdirect.integration.subscription.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appdirect.integration.configuration.AppDAOManagerImpl;
import com.appdirect.integration.entities.Response;
import com.appdirect.integration.entities.SubscriptionCancel;
import com.appdirect.integration.entities.SubscriptionChange;
import com.appdirect.integration.entities.SubscriptionOrder;

@Service("subscriptionManager")
@Scope("request")
public class SubscriptionManagerImpl extends AppDAOManagerImpl implements SubscriptionManager {

	@Autowired
	private SubscriptionDAO subscriptionDao = null;
	
	@Override
	public SubscriptionOrder getSubscriptionByParam(Object... objects) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response cancelSubscription(SubscriptionCancel order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateSubscriptionChange(SubscriptionChange order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addSubscriptionOrder(SubscriptionOrder order) throws Exception {
		return subscriptionDao.addSubscriptionOrder(order, jTemplate);
	}

}
