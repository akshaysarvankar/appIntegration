package com.appdirect.integration.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="response")
@XmlRootElement
public class Response {
	
	public enum ErrorCode{
		USER_ALREADY_EXISTS, USER_NOT_FOUND,
		ACCOUNT_NOT_FOUND, MAX_USERS_REACHED, INVALID_RESPONSE;
	}
	
	public Response() {
		this.errorCode = ErrorCode.INVALID_RESPONSE;
	}
	private String accountIdentifier;
	private ErrorCode errorCode;
	private String message;
	private boolean success;
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	

}
