package com.appdirect.integration.entities;


import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlType(name="account")
@XmlRootElement
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
	
	private String accountIdentifier;
	private String status;
	private String parentAccountId;
	private Date startDate;
	private Date updateDate;
	
	@XmlTransient
	@JsonIgnore
	private Integer companyId;
	
	public Account() {
		super();
	}
	
	public Account(Account account) {
		super();
		this.accountIdentifier = account.accountIdentifier;
		this.status = account.status;
		this.parentAccountId = account.parentAccountId;
		this.startDate = account.startDate;
		this.updateDate = account.updateDate;
		this.companyId = account.companyId;
	}
	
	
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

}
