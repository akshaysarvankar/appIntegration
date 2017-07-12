package com.appdirect.integration.entities;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="user")
@XmlRootElement
public class User {
	private String email;
	private String firstName;
	private String language;
	private String lastName;
	private String locale;
	private String openI;
	private String  uuid;
	private Address address;
	private Attribute attributes;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getOpenI() {
		return openI;
	}
	public void setOpenI(String openI) {
		this.openI = openI;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Attribute getAttributes() {
		return attributes;
	}
	public void setAttributes(Attribute attributes) {
		this.attributes = attributes;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	

}
