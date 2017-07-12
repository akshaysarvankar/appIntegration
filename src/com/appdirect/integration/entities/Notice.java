package com.appdirect.integration.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="notice")
@XmlRootElement
public class Notice {

	public enum NoticeType{
		REACTIVATED, DEACTIVATED, CLOSED;
	}
	private NoticeType type;
	private String message;
	public NoticeType getType() {
		return type;
	}
	public void setType(NoticeType type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
