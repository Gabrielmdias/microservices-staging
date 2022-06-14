package com.wit.subscriptionfacade.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.RemoteInvocationAwareMessageConverterAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wit.subscriptiondomain.Entity;
import com.wit.subscriptiondomain.util.Utils;

@Service
public class SendRequestService {

	private static final Logger LOGGER = LogManager.getLogger(SendRequestService.class);

	private RabbitTemplate rabbitTemplate;
	private Queue queue;

	@Autowired
	public SendRequestService(RabbitTemplate rabbitTemplate, Queue queue) {
		this.rabbitTemplate = rabbitTemplate;
		this.queue = queue;
	}

	public Entity sendSubscriptionMessage(Entity entity) {
		LOGGER.info("Sending message: {}", entity);
		entity.setRequestId(MDC.get(Utils.MDC_REQUEST_ID));
		rabbitTemplate.setMessageConverter(new RemoteInvocationAwareMessageConverterAdapter());
		try {
			return (Entity) rabbitTemplate.convertSendAndReceive(queue.getName(), entity);
		} catch (AmqpException e) {
			LOGGER.error(String.format("AmqpException: %s", e.getMessage()), e);
			throw e;
		}
	}

}
