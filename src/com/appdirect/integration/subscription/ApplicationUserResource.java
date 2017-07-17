package com.appdirect.integration.subscription;

import javax.ws.rs.BadRequestException;
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
import com.appdirect.integration.entities.Response.ErrorCode;
import com.appdirect.integration.entities.userevent.UserAssignment;
import com.appdirect.integration.entities.userevent.UserEvent;
import com.appdirect.integration.entities.userevent.UserUnassignment;
import com.appdirect.integration.entities.userevent.UserUpdated;
import com.appdirect.integration.subscription.dao.UserEventManager;

@Component
@Scope("request")
@Path("/userEvent")
public class ApplicationUserResource extends AppResource {

	@Autowired
	private UserEventManager userEventManager= null;
	
	@GET
	@Path("heartBeat")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response heartBeat(@Context UriInfo uri) throws Exception{
		Response response = new Response(true);
		return response;
		}
	

	@POST
	@Path("userAssignment")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response userAssignment(UserEvent userEvent, @Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {	
			if(userEvent == null) {
				throw new BadRequestException("User Event is null");
			}if(!(userEvent instanceof UserAssignment)) {
				throw new BadRequestException("User event is not of type User assignment");
			}
			setDataSource();
			response = userEventManager.userAssignment((UserAssignment) userEvent);
			return response;
		}catch(Exception e) {
			return new Response(ErrorCode.OPERATION_CANCELED, e.getMessage());
		}
	}
	
	@POST
	@Path("userUnassignment")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response userUnassignment(UserEvent userEvent, @Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {	
			if(userEvent == null) {
				throw new BadRequestException("User Event is null");
			}if(!(userEvent instanceof UserUnassignment)) {
				throw new BadRequestException("User event is not of type User unassignment");
			}
			setDataSource();
			response = userEventManager.userUnassignment((UserUnassignment) userEvent);
			return response;
		}catch(Exception e) {
			return new Response(ErrorCode.OPERATION_CANCELED, e.getMessage());
		}
	}
	
	@POST
	@Path("userUpdate")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response userUpdate(UserEvent userEvent, @Context UriInfo uri) throws Exception{
		Response response = new Response();
		try {	
			if(userEvent == null) {
				throw new BadRequestException("User Event is null");
			}if(!(userEvent instanceof UserUpdated)) {
				throw new BadRequestException("User event is not of type User update");
			}
			setDataSource();
			response = userEventManager.userUpdate((UserUpdated) userEvent);
			return response;
		}catch(Exception e) {
			return new Response(ErrorCode.OPERATION_CANCELED, e.getMessage());
		}
	}
	
}
