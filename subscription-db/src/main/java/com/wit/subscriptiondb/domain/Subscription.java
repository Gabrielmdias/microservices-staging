package com.wit.subscriptiondb.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private Integer packageId;
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_SUBSCRIPTION", updatable = false, nullable = false)
    private Date dataSubscription;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id", nullable = false)
	private User user;
	
	public Subscription() {}
	
	public Subscription(UUID id, Integer packageId, User user) {
		this.id = id;
		this.packageId = packageId;
		this.user = user;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public Integer getPackageId() {
		return packageId;
	}
	
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDataSubscription() {
		return dataSubscription;
	}

	public void setDataSubscription(Date dataSubscription) {
		this.dataSubscription = dataSubscription;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscription other = (Subscription) obj;
		return Objects.equals(id, other.id);
	}
	
}
