package com.wit.subscriptiondb.service.crud.adapter;

import com.wit.subscriptiondomain.Entity;

public interface Crud {

	public default Entity execute(Entity entity) {
		switch (entity.getOperacao()) {
		case CREATE:
			return create(entity);
		case DELETE:
			return delete(entity);
		case FIND_BY_ID:
			return findById(entity);
		case FIND_ALL:
			return findAll();
		default:
			throw new IllegalArgumentException("Unidentified operation");
		}
	}

	public Entity create(Entity entity);

	public Entity delete(Entity entity);

	public Entity findById(Entity entity);

	public Entity findAll();
}
