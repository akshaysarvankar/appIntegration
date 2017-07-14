package com.appdirect.integration.entities;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlType(name="order")
@XmlRootElement
public class Order {

	
	private String editionCode;
	private String pricingDuration;
	private List<Item> items;
	
	@XmlTransient
	@JsonIgnore
	private Integer orderId;
	
	@XmlTransient
	@JsonIgnore
	private Integer creatorId;
	
	@XmlTransient
	@JsonIgnore
	private Integer companyId;
	
	@XmlTransient
	@JsonIgnore
	private Integer editionId;
	
	@XmlTransient
	@JsonIgnore
	private Integer pricingDurationId;
	
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
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getEditionId() {
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

	public enum editionCode{
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
}	
