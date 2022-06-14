package com.wit.subscriptionfacade.exceptions;

public class LoyaltyPeriodNotReachedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LoyaltyPeriodNotReachedException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoyaltyPeriodNotReachedException(String message) {
		super(message);
	}
}