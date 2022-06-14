package com.wit.subscriptiondomain;

import java.util.UUID;

public interface Entity {

	public UUID getId();
	
	public Operation getOperacao();
	
	public String getRequestId();
	
	public void setRequestId(String requestId);
}
