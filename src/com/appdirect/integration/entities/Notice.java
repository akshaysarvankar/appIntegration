package com.appdirect.integration.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="notice")
@XmlRootElement
public class Notice {

	public enum NoticeType{
		REACTIVATED, DEACTIVATED, CLOSED, UPCOMING_INVOICE;
		
		public static boolean contains(String type) {
		    for (NoticeType c : NoticeType.values()) {
		        if (c.name().equals(type)) {
		            return true;
		        }
		    }
		    return false;
		}
	}
	private String type;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
