package com.appdirect.integration.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlType(name="response")
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class Response {
	
	public enum ErrorCode{
		USER_ALREADY_EXISTS, USER_NOT_FOUND,
		ACCOUNT_NOT_FOUND, MAX_USERS_REACHED, INVALID_RESPONSE, UNAUTHORIZED, OPERATION_CANCELED,
		CONFIGURATION_ERROR, PENDING, FORBIDDEN, BINDING_NOT_FOUND, TRANSPORT_ERROR, UNKNOWN_ERROR;
	}
	
	public Response() {
		this.setErrorCode(ErrorCode.INVALID_RESPONSE.name());
	}
	
	public Response(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
		this.success = true;
	}
	
	public Response(ErrorCode errorCode, String message) {
		this.setErrorCode(errorCode.name());
		this.setMessage(message);
		this.success = false;
	}
	private String accountIdentifier;
	private String errorCode;
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
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	

}
