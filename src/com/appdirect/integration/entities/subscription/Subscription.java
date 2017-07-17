package com.appdirect.integration.entities.subscription;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.appdirect.integration.entities.Creator;
import com.appdirect.integration.entities.MarketPlace;
import com.appdirect.integration.entities.Payload;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlRootElement
@XmlSeeAlso(value = {SubscriptionOrder.class, SubscriptionChange.class, SubscriptionNotice.class, SubscriptionCancel.class})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({@Type(value=SubscriptionOrder.class, name = "SUBSCRIPTION_ORDER"),
	@Type(value=SubscriptionChange.class, name = "SUBSCRIPTION_CHANGE"), 
	@Type(value=SubscriptionNotice.class, name = "SUBSCRIPTION_NOTICE"), 
	@Type(value=SubscriptionCancel.class, name = "SUBSCRIPTION_CANCEL")})
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Subscription {
	
	public enum Flag {
		STATELESS, DEVELOPMENT;
	}
	private MarketPlace marketplace;
	private String applicationUuid;
	private String flag;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
