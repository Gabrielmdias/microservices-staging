package com.wit.subscriptiondomain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createAt;
	private String location;
	private Contact contact;

	public Company(String name, Date createAt, String location, Contact contact) {
		this.name = name;
		this.createAt = createAt;
		this.location = location;
		this.contact = contact;
	}
	
	public Company(com.wit.packages.soap.sdk.wsdl.Company company) {
		this.name = company.getName();
		this.createAt = Objects.nonNull(company.getCreatedAt()) ? company.getCreatedAt().toGregorianCalendar().getTime() : null;
		this.location = company.getLocation();
		this.contact = Objects.nonNull(company.getContact()) ? new Contact(company.getContact()) : null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", createAt=" + createAt + ", location=" + location + ", contact="
				+ contact + "]";
	}

}
