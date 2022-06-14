package com.wit.subscriptionfacade.exceptions.resources;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wit.subscriptiondomain.exceptions.DatabaseException;
import com.wit.subscriptiondomain.exceptions.ObjectNotFoundException;
import com.wit.subscriptionfacade.exceptions.LoyaltyPeriodNotReachedException;

@ControllerAdvice
public class ResourceExceptionHandles {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, ServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(LoyaltyPeriodNotReachedException.class)
	public ResponseEntity<StandardError> objectNotUnsubscribe(LoyaltyPeriodNotReachedException e, ServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, ServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class, HttpMessageNotReadableException.class })
	public ResponseEntity<StandardError> handleConflict(RuntimeException e, ServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.CONFLICT.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

}
