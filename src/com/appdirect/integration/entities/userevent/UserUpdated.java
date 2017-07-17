package com.appdirect.integration.entities.userevent;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.appdirect.integration.entities.subscription.Subscription;

@XmlType(name="USER_UPDATED")
@XmlRootElement
public class UserUpdated extends UserEvent {

}
