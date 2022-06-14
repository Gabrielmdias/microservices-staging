package com.wit.subscriptiondb.mapper;

import com.wit.subscriptiondb.domain.User;
import com.wit.subscriptiondomain.UserDTO;

public interface UserMapper {

	public static UserDTO userToUserDto(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		return userDTO;
	}

	public static User userDtoToUser(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName());
	}
}
