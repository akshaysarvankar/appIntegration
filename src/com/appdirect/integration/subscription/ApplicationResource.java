package com.appdirect.integration.subscription;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.appdirect.integration.AppResource;
import com.appdirect.integration.entities.Response;
import com.appdirect.integration.entities.SubscriptionOrder;
import com.appdirect.integration.subscription.dao.SubscriptionManager;

@Component
@Scope("request")
@Path("/")
public class ApplicationResource extends AppResource {

	@Autowired
	private SubscriptionManager subscriptionManager= null;

	
	@GET
	@Path("get")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Object subscription(@Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {
			
			
			return response;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@POST
	@Path("addOrder")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response addSubscriptionOrder(SubscriptionOrder order, @Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {	
			setDataSource();
			response = subscriptionManager.addSubscriptionOrder(order);
					
			return response;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@POST
	@Path("cancelSubscription")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response cancelSubscription(SubscriptionOrder order, @Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {	
			//setDataSource()
			
			
			return response;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@POST
	@Path("updateSubscription")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updateSubscription(SubscriptionOrder order, @Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {	
			//setDataSource()
			
			
			return response;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
