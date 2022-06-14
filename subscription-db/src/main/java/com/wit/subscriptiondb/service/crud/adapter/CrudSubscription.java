package com.wit.subscriptiondb.service.crud.adapter;

import com.wit.subscriptiondb.service.SubscriptionService;
import com.wit.subscriptiondomain.Entity;
import com.wit.subscriptiondomain.SubscriptionDTO;

public class CrudSubscription implements Crud {

	private SubscriptionService subscriptionService;

	public CrudSubscription(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	@Override
	public Entity create(Entity entity) {
		return subscriptionService.create((SubscriptionDTO) entity);
	}

	@Override
	public Entity delete(Entity entity) {
		return subscriptionService.delete((SubscriptionDTO) entity);
	}

	@Override
	public Entity findById(Entity entity) {
		return subscriptionService.findById((SubscriptionDTO) entity);
	}

	@Override
	public Entity findAll() {
		return subscriptionService.findAll();
	}



}
