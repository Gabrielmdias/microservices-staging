package com.wit.subscriptiondomain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wit.packages.soap.sdk.wsdl.Package;

public class PackageDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String description;
	private BigDecimal price;
	private String currency;
	private int loyaltyPeriod;
	private String icon;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	private Company company;

	public PackageDTO() {
	}

	public PackageDTO(Builder builder){
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description; 
		this.price = builder.price;
		this.currency = builder.currency;
		this.loyaltyPeriod = builder.loyaltyPeriod;
		this.icon = builder.icon;
		this.startDate = builder.startDate;
		this.endDate = builder.endDate;
		this.company = builder.company;
    }

	public PackageDTO(Package packageApi) {
		this.id = packageApi.getId();
		this.name = packageApi.getName();
		this.description = packageApi.getDescription();
		this.price = packageApi.getPrice();
		this.currency = packageApi.getCurrency();
		this.loyaltyPeriod = packageApi.getLoyaltyPeriod();
		this.icon = packageApi.getIcon();
		this.startDate = Objects.nonNull(packageApi.getStartDate()) ? packageApi.getStartDate().toGregorianCalendar().getTime() : null;
		this.endDate = Objects.nonNull(packageApi.getEndDate()) ? packageApi.getEndDate().toGregorianCalendar().getTime() : null;
		this.company = Objects.nonNull(packageApi.getCompany()) ? new Company(packageApi.getCompany()) : null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getLoyaltyPeriod() {
		return loyaltyPeriod;
	}

	public void setLoyaltyPeriod(int loyaltyPeriod) {
		this.loyaltyPeriod = loyaltyPeriod;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "PackageDTO [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", currency=" + currency + ", loyaltyPeriod=" + loyaltyPeriod + ", icon=" + icon + ", startDate="
				+ startDate + ", endDate=" + endDate + ", company=" + company + "]";
	}

	public static class Builder {
		private int id;
		private String name;
		private String description;
		private BigDecimal price;
		private String currency;
		private int loyaltyPeriod;
		private String icon;
		private Date startDate;
		private Date endDate;
		private Company company;

		public PackageDTO build() {
			return new PackageDTO(this);
		}

		public Builder() {
		}

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}

		public Builder currency(String currency) {
			this.currency = currency;
			return this;
		}

		public Builder loyaltyPeriod(int loyaltyPeriod) {
			this.loyaltyPeriod = loyaltyPeriod;
			return this;
		}

		public Builder icon(String icon) {
			this.icon = icon;
			return this;
		}

		public Builder startDate(Date startDate) {
			this.startDate = startDate;
			return this;
		}

		public Builder endDate(Date endDate) {
			this.endDate = endDate;
			return this;
		}

		public Builder company(Company company) {
			this.company = company;
			return this;
		}

	}

}
