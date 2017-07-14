package com.appdirect.integration.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@XmlRootElement
@XmlSeeAlso(value = {})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({@Type(value=SubscriptionOrder.class, name = "SUBSCRIPTION_ORDER"),
	@Type(value=SubscriptionChange.class, name = "SUBSCRIPTION_CHANGE"), 
	@Type(value=SubscriptionCancel.class, name = "SUBSCRIPTION_CANCEL")})
public abstract class Subscription {
	
	public enum Flag {
		STATELESS, DEVELOPMENT;
	}
	private MarketPlace marketplace;
	private String applicationUuid;
	private Flag flag;
	private String returnUrl;
	private Creator creator;
	private Payload payload;
	private String accountIdentifier;
	
	public MarketPlace getMarketplace() {
		return marketplace;
	}
	public void setMarketplace(MarketPlace marketplace) {
		this.marketplace = marketplace;
	}
	public Creator getCreator() {
		return creator;
	}
	public void setCreator(Creator creator) {
		this.creator = creator;
	}
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	public String getApplicationUuid() {
		return applicationUuid;
	}
	public void setApplicationUuid(String applicationUuid) {
		this.applicationUuid = applicationUuid;
	}
	public Flag getFlag() {
		return flag;
	}
	public void setFlag(Flag flag) {
		this.flag = flag;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

}
