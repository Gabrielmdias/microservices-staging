package com.wit.subscriptiondb.service.crud.adapter;

import com.wit.subscriptiondb.service.SubscriptionService;
import com.wit.subscriptiondb.service.UserService;
import com.wit.subscriptiondomain.Entity;
import com.wit.subscriptiondomain.SubscriptionDTO;
import com.wit.subscriptiondomain.UserDTO;

public class CrudAdapter {

	private Crud crud;

	public CrudAdapter(Entity entity, UserService userService, SubscriptionService subscriptionService) {
		if (entity.getClass().isAssignableFrom(UserDTO.class)) {
			crud = new CrudUser(userService);
		} else if ((entity.getClass().isAssignableFrom(SubscriptionDTO.class))) {
			crud = new CrudSubscription(subscriptionService);
		}
	}

	public Crud getCrud() {
		return crud;
	}

	public void setCrud(Crud crud) {
		this.crud = crud;
	}

}
