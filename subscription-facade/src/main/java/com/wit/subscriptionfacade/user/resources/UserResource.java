package com.wit.subscriptionfacade.user.resources;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wit.subscriptiondomain.Entity;
import com.wit.subscriptiondomain.UserDTO;
import com.wit.subscriptionfacade.dto.UserPostRequestBody;
import com.wit.subscriptionfacade.mapper.UserMapper;
import com.wit.subscriptionfacade.user.service.UserService;

@RestController
@RequestMapping(value = "/usuario")
public class UserResource {

	private UserService userService;

	public UserResource(UserService userService) {
		this.userService = userService;
	}

	@PostMapping()
	public ResponseEntity<Entity> createUser(@RequestBody UserPostRequestBody userPostRequestBody) {
		UserDTO usuarioDTO = userService.createUser(UserMapper.INSTANCE.toUserDTO(userPostRequestBody));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Entity> deleteUser(@PathVariable UUID id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}

}
