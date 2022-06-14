package com.wit.subscriptionfacade.user.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.wit.subscriptiondomain.Operation;
import com.wit.subscriptiondomain.UserDTO;
import com.wit.subscriptionfacade.service.SendRequestService;

@Service
public class UserService {

	private SendRequestService sendRequestService;

	public UserService(SendRequestService sendRequestService) {
		this.sendRequestService = sendRequestService;
	}

	public UserDTO createUser(UserDTO userDTO){
		userDTO.setOperacao(Operation.CREATE);
		return (UserDTO) sendRequestService.sendSubscriptionMessage(userDTO);
	}

	public void deleteUser(UUID id) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(id);
		userDTO.setOperacao(Operation.DELETE);
		sendRequestService.sendSubscriptionMessage(userDTO);
	}
}
