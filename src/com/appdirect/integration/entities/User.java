package com.appdirect.integration.entities;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlType(name="user")
@XmlRootElement
public class User {
	public User() {
		super();
	}
	
	public User(User user) {
		super();
		this.userId = user.userId;
		this.accountId = user.accountId;
		this.email = user.email;
		this.firstName = user.firstName;
		this.language = user.language;
		this.lastName = user.lastName;
		this.fullName = user.fullName;
		this.locale = user.locale;
		this.openId = user.openId;
		this.uuid = user.uuid;
		this.address = user.address;
		this.statusId = user.statusId;
		this.attributes = user.attributes;
	}
	@XmlTransient
	@JsonIgnore
	private Integer userId;

	@XmlTransient
	@JsonIgnore
	private Integer accountId;
	private String email;
	private String firstName;
	private String language;
	private String lastName;
	private String fullName;
	private String locale;
	private String openId;
	private String  uuid;
	private Address address;
	
	@XmlTransient
	@JsonIgnore
	private Integer statusId;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	

}
