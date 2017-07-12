package com.appdirect.integration.subscription;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.appdirect.integration.entities.Response;
import com.appdirect.integration.entities.SubscriptionOrder;

@Component
@Scope("request")
@Path("/")
public class SubscrptionResource {
	
	@GET
	@Path("create")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response subscriptionOrder(@QueryParam("eventUrl") String eventUrl,@Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {
			response.setMessage(eventUrl);
			
			return response;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@GET
	@Path("cancel")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response subscriptionCancel(@QueryParam("eventUrl") String eventUrl,@Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {
			
			
			return response;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@GET
	@Path("change")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response subscriptionChange(@QueryParam("eventUrl") String eventUrl,@Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {
			
			
			return response;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
