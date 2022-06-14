package com.wit.subscriptiondomain;

import java.io.Serializable;

public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String msisdn;
	private String email;

	public Contact(String msisdn, String email) {
		this.msisdn = msisdn;
		this.email = email;
	}
	
	public Contact(com.wit.packages.soap.sdk.wsdl.Contact contact) {
		this.msisdn = contact.getMsisdn();
		this.email = contact.getEmail();
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ContactDTO [msisdn=" + msisdn + ", email=" + email + "]";
	}

}
