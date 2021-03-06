package com.appdirect.integration.subscription;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.appdirect.integration.AppResource;
import com.appdirect.integration.entities.Response;
import com.appdirect.integration.entities.Response.ErrorCode;
import com.appdirect.integration.entities.subscription.Subscription;
import com.appdirect.integration.entities.subscription.SubscriptionCancel;
import com.appdirect.integration.entities.subscription.SubscriptionChange;
import com.appdirect.integration.entities.subscription.SubscriptionNotice;
import com.appdirect.integration.entities.subscription.SubscriptionOrder;
import com.appdirect.integration.subscription.dao.SubscriptionManager;

@Component
@Scope("request")
@Path("/order")
public class ApplicationSubscriptionResource extends AppResource {

	@Autowired
	private SubscriptionManager subscriptionManager= null;

	
//	@GET
//	@Path("{id}/get")
//	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//	public Object subscription(@PathParam("id") String orderId,@Context UriInfo uri) throws Exception{
//		Response response = new Response();
//		try {
//			
//			
//			return response;
//		}catch(Exception e) {
//			throw new Exception(e.getMessage());
//		}
//	}
	
	@POST
	@Path("addOrder")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response addSubscriptionOrder(Subscription order, @Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {	
			if(order == null) {
				throw new BadRequestException("Subscription order is null");
			}if(!(order instanceof SubscriptionOrder)) {
				throw new BadRequestException("Order is not of type Subscription order");
			}
			setDataSource();
			response = subscriptionManager.addSubscriptionOrder((SubscriptionOrder) order);
			return response;
		}catch(Exception e) {
			return new Response(ErrorCode.OPERATION_CANCELED, e.getMessage());
		}
	}
	
	@POST
	@Path("cancelSubscription")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response cancelSubscription(Subscription order, @Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {	
			if(order == null) {
				throw new BadRequestException("Subscription cancel order is null");
			}if(!(order instanceof SubscriptionCancel)) {
				throw new BadRequestException("Order is not of type Subscription cancel");
			}
			setDataSource();
			response = subscriptionManager.cancelSubscription((SubscriptionCancel) order);
			return response;
		}catch(Exception e) {
			return new Response(ErrorCode.OPERATION_CANCELED, e.getMessage());
		}
	}
	
	@POST
	@Path("updateSubscription")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updateSubscription(Subscription order, @Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {	
			if(order == null) {
				throw new BadRequestException("Subscription change order is null");
			}if(!(order instanceof SubscriptionChange)) {
				throw new BadRequestException("Order is not of type Subscription change");
			}
			setDataSource();
			response = subscriptionManager.SubscriptionChange((SubscriptionChange) order);
			return response;
		}catch(Exception e) {
			return new Response(ErrorCode.OPERATION_CANCELED, e.getMessage());
		}
	}
	
	@POST
	@Path("subscriptionNotice")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response subscriptionNotice(Subscription order, @Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {	
			if(order == null) {
				throw new BadRequestException("Subscription notice order is null");
			}if(!(order instanceof SubscriptionNotice)) {
				throw new BadRequestException("Order is not of type Subscription notice");
			}
			setDataSource();
			response = subscriptionManager.SubscriptionNotice((SubscriptionNotice) order);
			return response;
		}catch(Exception e) {
			return new Response(ErrorCode.OPERATION_CANCELED, e.getMessage());
		}
	}
}
