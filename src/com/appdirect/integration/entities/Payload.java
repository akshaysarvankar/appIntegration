	package com.appdirect.integration.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlType(name="payload")
@XmlRootElement
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload {
private Account account;
private Company company;
private Order order;
private User user;
private Notice notice;
public Account getAccount() {
	return account;
}
public void setAccount(Account account) {
	this.account = account;
}
public Order getOrder() {
	return order;
}
public void setOrder(Order order) {
	this.order = order;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Company getCompany() {
	return company;
}
public void setCompany(Company company) {
	this.company = company;
}
public Notice getNotice() {
	return notice;
}
public void setNotice(Notice notice) {
	this.notice = notice;
}

}
