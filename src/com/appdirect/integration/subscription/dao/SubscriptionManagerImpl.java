package com.appdirect.integration.subscription.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appdirect.integration.configuration.AppDAOManagerImpl;
import com.appdirect.integration.entities.MarketPlace;
import com.appdirect.integration.entities.Order;
import com.appdirect.integration.entities.Response;
import com.appdirect.integration.entities.Response.ErrorCode;
import com.appdirect.integration.entities.Subscription;
import com.appdirect.integration.entities.SubscriptionCancel;
import com.appdirect.integration.entities.SubscriptionChange;
import com.appdirect.integration.entities.SubscriptionOrder;
import com.appdirect.integration.subscription.dao.marketplace.MarketPlaceDAO;

@Service("subscriptionManager")
@Scope("request")
public class SubscriptionManagerImpl extends AppDAOManagerImpl implements SubscriptionManager {

	@Autowired
	private MarketPlaceDAO marketplaceDAO= null;
	
	
	@Override
	public SubscriptionOrder getSubscriptionByOrderId(Object... objects) throws Exception {
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
	@Transactional
	public Response addSubscriptionOrder(SubscriptionOrder order) throws Exception {
		try {
		jTemplate.initTransaction();
		Order resultOrder = new Order();
		validateAndSetMarketPlaceOnOrder(resultOrder, order);
		verifyOrAddCreator(resultOrder, order);		
		
		SubscriptionOrder result = getSubscriptionByOrderId(resultOrder.getOrderId());
		return result!= null ? new Response(resultOrder.getAccountId().toString()) :  new Response();
		}catch(Exception e) {
			return new Response(ErrorCode.OPERATION_CANCELED, "");
		}
	}

	private void verifyOrAddCreator(Order resultOrder, SubscriptionOrder order) throws Exception {
		assertNull("Creator cannot be null", order.getCreator());
		assertNull("uuid of creator cannot be null", order.getCreator().getUuid());
		
		
	}

	private void validateAndSetMarketPlaceOnOrder(Order resultOrder, Subscription order) throws Exception {
		assertNull("Order cannot be null", order);
		assertNull("MarketPlace cannot be null", order.getMarketplace());
		assertNull("MarketPlace partnerName cannot be null", order.getMarketplace().getPartner());
		MarketPlace marketPlace = marketplaceDAO.getObject(order.getMarketplace().getPartner(), jTemplate);
		assertNull("MarketPlace not available", marketPlace);
		resultOrder.setMarketPlaceId(marketPlace.getMarketPlaceId());
	}

}
