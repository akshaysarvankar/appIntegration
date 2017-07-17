package com.appdirect.integration.subscription.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appdirect.integration.configuration.AppDAOManagerImpl;
import com.appdirect.integration.entities.Account;
import com.appdirect.integration.entities.Status.statusCode;
import com.appdirect.integration.entities.Address;
import com.appdirect.integration.entities.Company;
import com.appdirect.integration.entities.Creator;
import com.appdirect.integration.entities.Event;
import com.appdirect.integration.entities.Event.EventType;
import com.appdirect.integration.entities.MarketPlace;
import com.appdirect.integration.entities.Notice;
import com.appdirect.integration.entities.Notice.NoticeType;
import com.appdirect.integration.entities.Order;
import com.appdirect.integration.entities.Order.OrderType;
import com.appdirect.integration.entities.Payload;
import com.appdirect.integration.entities.Response;
import com.appdirect.integration.entities.Response.ErrorCode;
import com.appdirect.integration.entities.User;
import com.appdirect.integration.entities.subscription.Subscription;
import com.appdirect.integration.entities.subscription.SubscriptionCancel;
import com.appdirect.integration.entities.subscription.SubscriptionChange;
import com.appdirect.integration.entities.subscription.SubscriptionNotice;
import com.appdirect.integration.entities.subscription.SubscriptionOrder;
import com.appdirect.integration.entities.userevent.UserAssignment;
import com.appdirect.integration.entities.userevent.UserEvent;
import com.appdirect.integration.entities.userevent.UserUnassignment;
import com.appdirect.integration.entities.userevent.UserUpdated;
import com.appdirect.integration.subscription.dao.account.AccountDAO;
import com.appdirect.integration.subscription.dao.address.AddressDAO;
import com.appdirect.integration.subscription.dao.company.CompanyDAO;
import com.appdirect.integration.subscription.dao.creator.CreatorDAO;
import com.appdirect.integration.subscription.dao.event.EventDAO;
import com.appdirect.integration.subscription.dao.marketplace.MarketPlaceDAO;
import com.appdirect.integration.subscription.dao.order.OrderDAO;
import com.appdirect.integration.subscription.dao.user.UserDAO;

@Service("userEventManager")
@Scope("request")
public class UserEventManagerImpl extends AppDAOManagerImpl implements UserEventManager {

	public static final String ADD= "add";
	public static final String UPDATE= "update";
	
	@Autowired
	private MarketPlaceDAO marketplaceDAO= null;
	
	@Autowired
	private CreatorDAO creatorDAO= null;
	
	@Autowired
	private AddressDAO addressDAO= null;
	
	@Autowired
	private UserDAO userDAO= null;
	
	@Autowired
	private AccountDAO accountDAO= null;
	
	@Autowired
	private EventDAO eventDAO= null;
	
	@Override
	@Transactional
	public Response userAssignment(UserAssignment userEvent) throws Exception {
		try {
		jTemplate.initTransaction();
		Event tempEvent = new Event();
		Event resultEvent = null;
		tempEvent.setEventTypeId(EventType.USER_ASSIGNMENT.getId());
		validateAndSetMarketPlaceOnEvent(tempEvent, userEvent);
		verifyOrAddCreator(tempEvent, userEvent);		
		Payload payload = userEvent.getPayload();
		if(payload != null && payload.getAccount()!= null && payload.getUser()!= null) {
			Account account = accountDAO.getObject(payload.getAccount().getAccountIdentifier(), jTemplate);
			if(account== null) {
				return new Response(ErrorCode.ACCOUNT_NOT_FOUND,"Account you are trying to cancel is not available" );
			}else {
				User user = payload.getUser();
				if(userDAO.getObject(user.getUuid(), Integer.valueOf(account.getAccountIdentifier()), jTemplate)!= null) {
					return new Response(ErrorCode.USER_ALREADY_EXISTS,"User information exists, please use user update to change information" );
				}else {
				//add Company and account
					User resultUser = addOrUpdateUser(payload.getUser(), account, ADD);
					tempEvent.setAccountId(Integer.valueOf(account.getAccountIdentifier()));
					tempEvent.setUserId(resultUser.getUserId());
					resultEvent = eventDAO.add(tempEvent, jTemplate);
					if(resultEvent!= null) {
						jTemplate.commit();
						return new Response(true);
					}else {
					jTemplate.rollback();
					return new Response(ErrorCode.TRANSPORT_ERROR, "Error while adding user or event information");
					}
				}
			}
		}else {
			jTemplate.rollback();
			return new Response(ErrorCode.CONFIGURATION_ERROR, "Payload information unavailable");
		}
		}catch(Exception e) {
			jTemplate.rollback();
			return new Response(ErrorCode.OPERATION_CANCELED, e.getMessage());
		}
	}

	@Override
	public Response userUnassignment(UserUnassignment userEvent) throws Exception {
		try {
		jTemplate.initTransaction();
		Event tempEvent = new Event();
		Event resultEvent = null;
		tempEvent.setEventTypeId(EventType.USER_ASSIGNMENT.getId());
		validateAndSetMarketPlaceOnEvent(tempEvent, userEvent);
		verifyOrAddCreator(tempEvent, userEvent);		
		Payload payload = userEvent.getPayload();
		if(payload != null && payload.getAccount()!= null && payload.getUser()!= null) {
			Account account = accountDAO.getObject(payload.getAccount().getAccountIdentifier(), jTemplate);
			if(account== null) {
				return new Response(ErrorCode.ACCOUNT_NOT_FOUND,"Account you are trying to cancel is not available" );
			}else {
				User user = userDAO.getObject(payload.getUser().getUuid(), Integer.valueOf(account.getAccountIdentifier()), jTemplate);
				if(user== null) {
					return new Response(ErrorCode.USER_NOT_FOUND,"User information not available" );
				}else {
					//update user status to cancelled
					user.setStatusId(statusCode.CANCELLED.getId());
					User resultUser = addOrUpdateUser(user, account, UPDATE);
					tempEvent.setAccountId(Integer.valueOf(account.getAccountIdentifier()));
					tempEvent.setUserId(resultUser.getUserId());
					resultEvent = eventDAO.add(tempEvent, jTemplate);
					if(resultEvent!= null) {
						jTemplate.commit();
						return new Response(true);
				}else {
					jTemplate.rollback();
					return new Response(ErrorCode.TRANSPORT_ERROR, "Error while removing user  or adding event information");
				}
			}
			}
		}else {
			jTemplate.rollback();
			return new Response(ErrorCode.CONFIGURATION_ERROR, "Payload information unavailable");
		}
		}catch(Exception e) {
			jTemplate.rollback();
			return new Response(ErrorCode.OPERATION_CANCELED, e.getMessage());
		}
	}

	@Override
	public Response userUpdate(UserUpdated userEvent) throws Exception {
		try {
		jTemplate.initTransaction();
		Event tempEvent = new Event();
		Event resultEvent = null;
		tempEvent.setEventTypeId(EventType.USER_ASSIGNMENT.getId());
		validateAndSetMarketPlaceOnEvent(tempEvent, userEvent);
		verifyOrAddCreator(tempEvent, userEvent);		
		Payload payload = userEvent.getPayload();
		if(payload != null && payload.getAccount()!= null && payload.getUser()!= null) {
			Account account = accountDAO.getObject(payload.getAccount().getAccountIdentifier(), jTemplate);
			if(account== null) {
				return new Response(ErrorCode.ACCOUNT_NOT_FOUND,"Account you are trying to cancel is not available" );
			}else {
				User user = payload.getUser();
				User tempUser = userDAO.getObject(payload.getUser().getUuid(), Integer.valueOf(account.getAccountIdentifier()), jTemplate);
				if(tempUser == null) {
					return new Response(ErrorCode.USER_NOT_FOUND,"User information not available" );
				}else {
					user.setUserId(tempUser.getUserId());
					User resultUser = addOrUpdateUser(user, account, UPDATE);
					tempEvent.setAccountId(Integer.valueOf(account.getAccountIdentifier()));
					tempEvent.setUserId(resultUser.getUserId());
					resultEvent = eventDAO.add(tempEvent, jTemplate);
					if(resultEvent!= null) {
						jTemplate.commit();
						return new Response(true);
				}else {
					jTemplate.rollback();
					return new Response(ErrorCode.TRANSPORT_ERROR, "Error while ipdating user  or adding event information");
				}
			}
			}
		}else {
			jTemplate.rollback();
			return new Response(ErrorCode.CONFIGURATION_ERROR, "Payload information unavailable");
		}
		}catch(Exception e) {
			jTemplate.rollback();
			return new Response(ErrorCode.OPERATION_CANCELED, e.getMessage());
		}
	}

	private void verifyOrAddCreator(Event resultEvent, UserEvent userEvent) throws Exception {
		assertNull("Creator cannot be null", userEvent.getCreator());
		assertNull("uuid of creator cannot be null", userEvent.getCreator().getUuid());
		Creator creator = creatorDAO.getObject(userEvent.getCreator().getUuid(), jTemplate);
		if(creator == null) {
			creator = creatorDAO.add(userEvent.getCreator(), jTemplate);
			if(userEvent.getCreator().getAddress()!= null) {
				//add address
				Address address = userEvent.getCreator().getAddress();
				address.setId(creator.getCreatorId());
				Address returnAddress = addressDAO.add(address, Creator.class, jTemplate);
				assertNull("Address add for creator failed",returnAddress);
			}
		}
		resultEvent.setCreatorId(creator.getCreatorId());
	}

	private void validateAndSetMarketPlaceOnEvent(Event resultEvent, UserEvent userEvent) throws Exception {
		assertNull("UserEvent cannot be null", userEvent);
		assertNull("MarketPlace cannot be null", userEvent.getMarketplace());
		assertNull("MarketPlace partnerName cannot be null", userEvent.getMarketplace().getPartner());
		MarketPlace marketPlace = marketplaceDAO.getObject(userEvent.getMarketplace().getPartner(), jTemplate);
		assertNull("MarketPlace not available", marketPlace);
		resultEvent.setMarketPlaceId(marketPlace.getMarketPlaceId());
	}
	
	private User addOrUpdateUser(User user, Account account, String event) throws Exception{
		User tempUser= new User(user);
		if(event.equals(ADD)) {
			tempUser.setAccountId(Integer.valueOf(account.getAccountIdentifier()));
			tempUser.setStatusId(statusCode.ACTIVE.getId());
			User resultUser = userDAO.add(tempUser, jTemplate);
			if(tempUser.getAddress()!= null) {
				//add address
				Address address = tempUser.getAddress();
				address.setId(resultUser.getUserId());
				Address returnAddress = addressDAO.add(address, User.class, jTemplate);
				assertNull("Address add for creator failed",returnAddress);
			}
			return resultUser;
		}else if(event.equals(UPDATE)) {
			tempUser.setAccountId(Integer.valueOf(account.getAccountIdentifier()));
			User resultUser= userDAO.update(tempUser, jTemplate);
			if(tempUser.getAddress()!= null) {
				//add address
				Address address = tempUser.getAddress();
				address.setId(resultUser.getUserId());
				Address returnAddress = addressDAO.update(address, User.class, jTemplate);
				assertNull("Address update for user failed",returnAddress);
			}
			return resultUser;
		}else {
			throw new Exception("Event to be operate on user is not speified");
		}
	}

}
