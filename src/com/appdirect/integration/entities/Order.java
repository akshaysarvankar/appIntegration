package com.appdirect.integration.entities;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlType(name="order")
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class Order {

	private String editionCode;
	private String pricingDuration;
	private List<Item> items;
	
	@XmlTransient
	@JsonIgnore
	private Date startDate;
	
	@XmlTransient
	@JsonIgnore
	private Date competionDate;
	
	@XmlTransient
	@JsonIgnore
	private Integer orderId;
	
	@XmlTransient
	@JsonIgnore
	private Integer orderTypeId;
	
	@XmlTransient
	@JsonIgnore
	private Integer creatorId;
	
	
	/*@XmlTransient
	@JsonIgnore
	private Integer editionId;
	
	@XmlTransient
	@JsonIgnore
	private Integer pricingDurationId;
	*/
	@XmlTransient
	@JsonIgnore
	private Integer marketPlaceId;
	
	@XmlTransient
	@JsonIgnore
	private Integer accountId;
	
	
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	/*public Integer getEditionId() {
		return editionId;
	}
	public void setEditionId(Integer editionId) {
		this.editionId = editionId;
	}
	public Integer getPricingDurationId() {
		return pricingDurationId;
	}
	public void setPricingDurationId(Integer pricingDurationId) {
		this.pricingDurationId = pricingDurationId;
	}
	*/
	public String getEditionCode() {
		return editionCode;
	}
	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}
	public String getPricingDuration() {
		return pricingDuration;
	}
	public void setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public Integer getOrderTypeId() {
		return orderTypeId;
	}
	public void setOrderTypeId(Integer orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

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

	public enum OrderType{
		SUBSCRIPTION_ORDER(1, "SUBSCRIPTION_ORDER"),
		SUBSCRIPTION_CHANGE(2, "SUBSCRIPTION_CHANGE"),
		SUBSCRIPTION_CANCEL(3, "SUBSCRIPTION_CANCEL"),
		SUBSCRIPTION_NOTICE(4, "SUBSCRIPTION_NOTICE"),
		USER_ASSIGNMENT(5, "USER_ASSIGNMENT"),
		USER_UNASSIGNMENT(6, "USER_UNASSIGNMENT"),
		USER_UPDATED(7, "USER_UPDATED");
		
	private final int id;
	private final String code;

	OrderType(int id, String code) {
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
	
	/*public enum editionCode{
		STANDARD(1, "Standard"),
		DME(2, "DME");
		
	private final int id;
	private final String code;
	
	
	editionCode(int id, String code) {
		this.id = id;
		this.code = code;
	}

	private int id() {
		return id;
	}

	private String code() {
		return code;
	}
	
	public Integer getId(String code) {
		if(this.code == code) {
			return this.id;
		}else {
			return 0;
		}
	}
	
	
	}
	
	public enum pricingDuration{
		DAILY(1, "DAILY"),
		MONTHLY(2, "MONTHLY");
		
	private final int id;
	private final String code;

	pricingDuration(int id, String code) {
		this.id = id;
		this.code = code;
	}

	private int id() {
		return id;
	}

	private String code() {
		return code;
	}
	
	public Integer getId(String code) {
		if(this.code == code) {
			return this.id;
		}else {
			return 0;
		}
	}
	}
*/
}	
