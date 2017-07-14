package com.appdirect.integration.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="account")
@XmlRootElement
public class Account {
	
	private String accountIdentifier;
	private String status;
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
	
	public Integer getId(String code) {
		if(this.code == code) {
			return this.id;
		}else {
			return 0;
		}
	}
	
	
	}

}
