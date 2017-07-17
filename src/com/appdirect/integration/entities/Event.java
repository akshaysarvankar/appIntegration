package com.appdirect.integration.entities;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//@XmlType(name="order")
//@XmlRootElement
//@JsonInclude(Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

	private Date startDate;
	private Date competionDate;
	private Integer eventId;
	private Integer eventTypeId;
	private Integer creatorId;
	private Integer marketPlaceId;
	private Integer accountId;
	private Integer userId;
	
	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getCompetionDate() {
		return competionDate;
	}



	public void setCompetionDate(Date competionDate) {
		this.competionDate = competionDate;
	}


	public Integer getCreatorId() {
		return creatorId;
	}



	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}



	public Integer getMarketPlaceId() {
		return marketPlaceId;
	}



	public void setMarketPlaceId(Integer marketPlaceId) {
		this.marketPlaceId = marketPlaceId;
	}



	public Integer getAccountId() {
		return accountId;
	}



	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public Integer getEventId() {
		return eventId;
	}



	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}



	public Integer getEventTypeId() {
		return eventTypeId;
	}



	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}



	public enum EventType{
		
		USER_ASSIGNMENT(1, "USER_ASSIGNMENT"),
		USER_UNASSIGNMENT(2, "USER_UNASSIGNMENT"),
		USER_UPDATED(3, "USER_UPDATED");
		
	private final int id;
	private final String code;

	EventType(int id, String code) {
		this.id = id;
		this.code = code;
	}

	private int id() {
		return id;
	}

	private String code() {
		return code;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getCode() {
		return this.code;
	}
	}
}	
