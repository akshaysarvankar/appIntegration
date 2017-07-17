package com.appdirect.integration.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

public class Status {
	
	public Map<Integer, String> status = new HashMap<Integer, String>();
	public Status(){
		status.put(statusCode.ACTIVE.getId(),statusCode.ACTIVE.getCode());
		status.put(statusCode.INITIALIZED.getId(),statusCode.INITIALIZED.getCode());
		status.put(statusCode.FAILED.getId(),statusCode.FAILED.getCode());
		status.put(statusCode.FREE_TRIAL.getId(),statusCode.FREE_TRIAL.getCode());
		status.put(statusCode.FREE_TRIAL_EXPIRED.getId(),statusCode.FREE_TRIAL_EXPIRED.getCode());
		status.put(statusCode.SUSPENDED.getId(),statusCode.SUSPENDED.getCode());
		status.put(statusCode.CANCELLED.getId(),statusCode.CANCELLED.getCode());
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
	
	public <T, E> Integer getKeyByValue(String value) {
	    for (Entry<Integer, String> entry : status.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}

}
