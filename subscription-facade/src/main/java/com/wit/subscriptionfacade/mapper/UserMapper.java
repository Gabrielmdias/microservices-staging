package com.wit.subscriptionfacade.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.wit.subscriptiondomain.UserDTO;
import com.wit.subscriptionfacade.dto.UserPostRequestBody;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	public UserDTO toUserDTO(UserPostRequestBody userPostRequestBody);

	public UserPostRequestBody toUserPostRequestBody(UserDTO userDTO);
}
