package com.wit.subscriptiondb.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wit.subscriptiondb.domain.Subscription;
import com.wit.subscriptiondb.domain.User;
import com.wit.subscriptiondb.mapper.UserMapper;
import com.wit.subscriptiondb.repository.UserRepository;
import com.wit.subscriptiondomain.UserDTO;
import com.wit.subscriptiondomain.exceptions.DatabaseException;
import com.wit.subscriptiondomain.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	private static final Logger LOGGER = LogManager.getLogger(UserService.class);

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDTO findById(UserDTO userDTO) {
		LOGGER.info("Find user");
		Optional<User> user = userRepository.findById(userDTO.getId());
		if (user.isEmpty()) {
			LOGGER.error("Error find user");
			throw new ObjectNotFoundException(
					"Objeto nao encontrado Id: " + userDTO.getId() + ", Tipo: " + Subscription.class.getName());
		}
		return UserMapper.userToUserDto(user.get());
	}

	public List<UserDTO> findAll() {
		LOGGER.info("Find all users");
		return userRepository.findAll().stream().map(UserMapper::userToUserDto)
				.collect(Collectors.toList());
	}

	public UserDTO delete(UserDTO userDTO) {
		try {
			userRepository.deleteById(userDTO.getId());
			LOGGER.info("User successfully deleted");
			return userDTO;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error deleting user", e);
			throw new ObjectNotFoundException(
					"Objeto nao encontrado Id: " + userDTO.getId() + ", Tipo: " + User.class.getName());
		} catch (DataIntegrityViolationException e) {
			LOGGER.error("Error deleting user, is assigned to another data", e);
			throw new DatabaseException("Error deleting user, is assigned to another data, id: " + userDTO.getId()
					+ ", Tipo: " + User.class.getName());
		}
	}

	public UserDTO create(UserDTO userDTO) {
		LOGGER.info("Create user: {}", userDTO);
		User user = userRepository.save(UserMapper.userDtoToUser(userDTO));
		return UserMapper.userToUserDto(user);
	}

}
