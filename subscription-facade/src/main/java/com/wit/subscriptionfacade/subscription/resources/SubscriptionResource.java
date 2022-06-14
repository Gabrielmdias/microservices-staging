package com.wit.subscriptionfacade.subscription.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wit.subscriptiondomain.SubscriptionDTO;
import com.wit.subscriptiondomain.UserDTO;
import com.wit.subscriptionfacade.subscription.service.SubscriptionService;

@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionResource {
	
	private SubscriptionService subscriptionService;
	
	public SubscriptionResource(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}
	
	@GetMapping()
	public ResponseEntity<List<SubscriptionDTO>> findAll() {
		List<SubscriptionDTO> subscriptions = subscriptionService.findAll();
		return ResponseEntity.ok().body(subscriptions);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SubscriptionDTO> findById(@PathVariable String id) {
		SubscriptionDTO resp = subscriptionService.findByIdSubscription(UUID.fromString(id));
		return ResponseEntity.ok().body(resp);
	}
	
	@PostMapping(value = "/{id}")
	public ResponseEntity<SubscriptionDTO> createSubscription(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
		SubscriptionDTO subDTO = subscriptionService.createSubscription(id, userDTO.getId());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(subDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteSubscription(@PathVariable UUID id) {
		subscriptionService.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}
