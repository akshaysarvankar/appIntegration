package com.appdirect.integration.entities;


import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlType(name="account")
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class Account {
	
	private String accountIdentifier;
	private String status;
	private String parentAccountId;
	private Date startDate;
	private Date updateDate;
	
	@XmlTransient
	@JsonIgnore
	private Integer companyId;
	
	@XmlTransient
	@JsonIgnore
	private Integer userId;
	
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getParentAccountId() {
		return parentAccountId;
	}
	public void setParentAccountId(String parentAccountId) {
		this.parentAccountId = parentAccountId;
	}


	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public enum statusCode{
		INITIALIZED(1, "INITIALIZED"),
		FAILED(2, "FAILED"),
		FREE_TRIAL(3, "FREE_TRIAL"),
		FREE_TRIAL_EXPIRED(4, "FREE_TRIAL_EXPIRED"),
		ACTIVE(5, "ACTIVE"),
		SUSPENDED(6, "SUSPENDED"),
		CANCELLED(7, "CANCELLED");
		
	private final int id;
	private final String code;

	statusCode(int id, String code) {
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
