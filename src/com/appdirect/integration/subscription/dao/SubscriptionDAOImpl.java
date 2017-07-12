package com.appdirect.integration.subscription.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.appdirect.integration.configuration.AppDAOImpl;
import com.appdirect.integration.entities.Response;
import com.appdirect.integration.entities.SubscriptionCancel;
import com.appdirect.integration.entities.SubscriptionChange;
import com.appdirect.integration.entities.SubscriptionOrder;

@Repository("SubscriptionDAO")
public class SubscriptionDAOImpl extends AppDAOImpl implements SubscriptionDAO {
	
	@Override
	public PreparedStatementCreator createInsertStatement(Object entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatementCreator createUpdateStatement(Object entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatementCreator createDeleteStatement(Object entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addSubscriptionOrder(SubscriptionOrder order, JdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return new Response();
	}

	@Override
	public Response cancelSubscription(SubscriptionCancel order, JdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateSubscriptionChange(SubscriptionChange order, JdbcTemplate jTemplate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	



}
