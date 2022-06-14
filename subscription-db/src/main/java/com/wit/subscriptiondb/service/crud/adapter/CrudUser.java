package com.wit.subscriptiondb.service.crud.adapter;

import com.wit.subscriptiondb.service.UserService;
import com.wit.subscriptiondomain.Entity;
import com.wit.subscriptiondomain.UserDTO;

public class CrudUser implements Crud {

	private UserService userService;

	public CrudUser(UserService userService) {
		this.userService = userService;
	}

	public Entity create(Entity entity) {
		return userService.create((UserDTO) entity);
	}

	public Entity delete(Entity entity) {
		return userService.delete((UserDTO) entity);
	}

	@Override
	public Entity findById(Entity entity) {
		return userService.findById((UserDTO) entity);
	}

	@Override
	public Entity findAll() {
		return null;
	}

}
