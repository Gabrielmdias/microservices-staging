package com.wit.subscriptiondomain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SubscriptionDTO implements Serializable, Entity {

	private static final long serialVersionUID = 1L;

	private UUID id;
	private Integer packageId;
	private Operation operacao;
	private Date dataSubscription;
	private UUID userId;
	private String requestId;

	public SubscriptionDTO() {
	}

	public SubscriptionDTO(UUID id, Integer packageId) {
		this.id = id;
		this.packageId = packageId;
	}

	@Override
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

	@JsonIgnore
	@Override
	public Operation getOperacao() {
		return operacao;
	}

	public void setOperacao(Operation operacao) {
		this.operacao = operacao;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public Date getDataSubscription() {
		return dataSubscription;
	}

	public void setDataSubscription(Date dataSubscription) {
		this.dataSubscription = dataSubscription;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@JsonIgnore
	@Override
	public String getRequestId() {
		return requestId;
	}
	
	@Override
	public String toString() {
		return "SubscriptionDTO [id=" + id + ", packageId=" + packageId + ", operacao=" + operacao + ", userId="
				+ userId + "]";
	}

}
