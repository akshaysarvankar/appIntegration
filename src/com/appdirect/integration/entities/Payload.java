	package com.appdirect.integration.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="payload")
@XmlRootElement
public class Payload {
private Account account;
private Company company;
private Order order;
private User user;
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

}
