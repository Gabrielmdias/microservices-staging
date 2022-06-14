package com.wit.subscriptiondomain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SubscriptionListDTO implements Serializable, Entity {
	private static final long serialVersionUID = 1L;
	
	private List<SubscriptionDTO> subscriptionsDto;
	private String requestId;

	public SubscriptionListDTO() {
	}

	public SubscriptionListDTO(List<SubscriptionDTO> subscriptionsDto) {
		this.subscriptionsDto = subscriptionsDto;
	}

	public List<SubscriptionDTO> getSubscriptionsDto() {
		return subscriptionsDto;
	}

	public void setSubscriptionsDto(List<SubscriptionDTO> subscriptionsDto) {
		this.subscriptionsDto = subscriptionsDto;
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
	public UUID getId() {
		return null;
	}

	@Override
	public Operation getOperacao() {
		return Operation.FIND_ALL;
	}

}
