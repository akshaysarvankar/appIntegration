package com.appdirect.integration.subscription.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appdirect.integration.configuration.AppDAOManagerImpl;
import com.appdirect.integration.entities.Account;
import com.appdirect.integration.entities.Address;
import com.appdirect.integration.entities.Company;
import com.appdirect.integration.entities.Creator;
import com.appdirect.integration.entities.MarketPlace;
import com.appdirect.integration.entities.Order;
import com.appdirect.integration.entities.Order.OrderType;
import com.appdirect.integration.entities.Payload;
import com.appdirect.integration.entities.Response;
import com.appdirect.integration.entities.Response.ErrorCode;
import com.appdirect.integration.entities.Subscription;
import com.appdirect.integration.entities.SubscriptionCancel;
import com.appdirect.integration.entities.SubscriptionChange;
import com.appdirect.integration.entities.SubscriptionOrder;
import com.appdirect.integration.entities.Account.statusCode;
import com.appdirect.integration.subscription.dao.account.AccountDAO;
import com.appdirect.integration.subscription.dao.address.AddressDAO;
import com.appdirect.integration.subscription.dao.company.CompanyDAO;
import com.appdirect.integration.subscription.dao.creator.CreatorDAO;
import com.appdirect.integration.subscription.dao.marketplace.MarketPlaceDAO;
import com.appdirect.integration.subscription.dao.order.OrderDAO;

@Service("subscriptionManager")
@Scope("request")
public class SubscriptionManagerImpl extends AppDAOManagerImpl implements SubscriptionManager {

	@Autowired
	private MarketPlaceDAO marketplaceDAO= null;
	
	@Autowired
	private CreatorDAO creatorDAO= null;
	
	@Autowired
	private AddressDAO addressDAO= null;
	
	@Autowired
	private CompanyDAO companyDAO= null;
	
	@Autowired
	private AccountDAO accountDAO= null;
	
	@Autowired
	private OrderDAO orderDAO= null;
	
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
	public Response SubscriptionChange(SubscriptionChange order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Response addSubscriptionOrder(SubscriptionOrder order) throws Exception {
		try {
		jTemplate.initTransaction();
		Order tempOrder = new Order();
		Order resultOrder = null;
		tempOrder.setOrderTypeId(OrderType.SUBSCRIPTION_ORDER.getId());
		validateAndSetMarketPlaceOnOrder(tempOrder, order);
		verifyOrAddCreator(tempOrder, order);		
		Payload payload = order.getPayload();
		if(payload.getCompany()!= null) {
			Company company = payload.getCompany();
			if(companyDAO.getObject(company.getUuid(), jTemplate)!= null) {
				return new Response(ErrorCode.USER_ALREADY_EXISTS,"Company information exists, please use subscription change" );
			}else {
				//add Company and account
				Account resultAccount = addAccount(companyDAO.add(company, jTemplate));
				tempOrder.setAccountId(Integer.valueOf(resultAccount.getAccountIdentifier()));
				tempOrder.setEditionCode(payload.getOrder().getEditionCode());
				tempOrder.setPricingDuration(payload.getOrder().getPricingDuration());
				resultOrder = orderDAO.add(tempOrder, jTemplate);
				if(resultOrder!= null) {
					jTemplate.commit();
					return new Response(resultOrder.getAccountId().toString());
				}else {
					jTemplate.rollback();
					return new Response(ErrorCode.TRANSPORT_ERROR, "Error while adding account and order information");
				}
			}
		}else {
			return new Response(ErrorCode.CONFIGURATION_ERROR, "Company information unavailable");
		}
		}catch(Exception e) {
			jTemplate.rollback();
			return new Response(ErrorCode.OPERATION_CANCELED, e.getMessage());
		}
	}

	private void verifyOrAddCreator(Order resultOrder, SubscriptionOrder order) throws Exception {
		assertNull("Creator cannot be null", order.getCreator());
		assertNull("uuid of creator cannot be null", order.getCreator().getUuid());
		Creator creator = creatorDAO.getObject(order.getCreator().getUuid(), jTemplate);
		if(creator == null) {
			creator = creatorDAO.add(order.getCreator(), jTemplate);
			if(order.getCreator().getAddress()!= null) {
				//add address
				Address address = addressDAO.add(order.getCreator().getAddress(), Creator.class, jTemplate);
				assertNull("Address add for creator failed",address);
			}
		}
		resultOrder.setCreatorId(creator.getCreatorId());
		
		
	}

	private void validateAndSetMarketPlaceOnOrder(Order resultOrder, Subscription order) throws Exception {
		assertNull("Order cannot be null", order);
		assertNull("MarketPlace cannot be null", order.getMarketplace());
		assertNull("MarketPlace partnerName cannot be null", order.getMarketplace().getPartner());
		MarketPlace marketPlace = marketplaceDAO.getObject(order.getMarketplace().getPartner(), jTemplate);
		assertNull("MarketPlace not available", marketPlace);
		resultOrder.setMarketPlaceId(marketPlace.getMarketPlaceId());
	}
	
	private Account addAccount(Company company) throws Exception{
		Account account= new Account();
		account.setCompanyId(company.getCompanyId());
		account.setStatus(statusCode.INITIALIZED.getCode());
		return accountDAO.add(account, jTemplate);
	}

}
