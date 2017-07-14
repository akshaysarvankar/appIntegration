package com.appdirect.integration.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlType(name="marketplace")
@XmlRootElement
public class MarketPlace {
	

	@XmlTransient
	@JsonIgnore
	private Integer marketPlaceId;
	private String baseUrl;
	private String partner;
	
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	
	public Integer getMarketPlaceId() {
		return marketPlaceId;
	}
	public void setMarketPlaceId(Integer marketPlaceId) {
		this.marketPlaceId = marketPlaceId;
	}

}
