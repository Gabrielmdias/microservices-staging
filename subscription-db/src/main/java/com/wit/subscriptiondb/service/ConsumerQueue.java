package com.wit.subscriptiondb.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.logging.MDC;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.wit.subscriptiondb.service.crud.adapter.CrudAdapter;
import com.wit.subscriptiondomain.Entity;
import com.wit.subscriptiondomain.util.Utils;

@Component
public class ConsumerQueue {
	
	private static final Logger LOGGER = LogManager.getLogger(ConsumerQueue.class);
	
	private UserService userService;
	private SubscriptionService subscriptionService;
	
	public ConsumerQueue(UserService userService, SubscriptionService subscriptionService) {
		this.userService = userService;
		this.subscriptionService = subscriptionService;
	}

	@RabbitListener(queues = "queue-subscription", returnExceptions = "true")
	public Object observer(Entity entity) {
		
		MDC.put(Utils.MDC_REQUEST_ID, entity.getRequestId());
		LOGGER.info("Receiving message: {}",  entity);
	
		CrudAdapter crudAdapter = new CrudAdapter(entity, userService, subscriptionService);
		return crudAdapter.getCrud().execute(entity);
	}

}
