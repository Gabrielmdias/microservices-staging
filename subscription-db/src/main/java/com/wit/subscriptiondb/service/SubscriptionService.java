package com.wit.subscriptiondb.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wit.subscriptiondb.domain.Subscription;
import com.wit.subscriptiondb.mapper.SubscriptionMapper;
import com.wit.subscriptiondb.repository.SubscriptionRepository;
import com.wit.subscriptiondomain.SubscriptionDTO;
import com.wit.subscriptiondomain.SubscriptionListDTO;
import com.wit.subscriptiondomain.exceptions.DatabaseException;
import com.wit.subscriptiondomain.exceptions.ObjectNotFoundException;

@Service
public class SubscriptionService {

	private static final Logger LOGGER = LogManager.getLogger(SubscriptionService.class);

	private SubscriptionRepository subscriptionRepository;

	public SubscriptionService(SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}

	public SubscriptionDTO findById(SubscriptionDTO subscriptionDTO) {
		LOGGER.info("Find subscription");
		Optional<Subscription> subscription = subscriptionRepository.findById(subscriptionDTO.getId());
		if (subscription.isEmpty()) {
			LOGGER.error("Error find subscription");
			throw new ObjectNotFoundException(
					"Object not found Id: " + subscriptionDTO.getId() + ", type: " + Subscription.class.getName());
		}
		return SubscriptionMapper.subscriptionToSubscriptionDTO(subscription.get());
	}

	public SubscriptionListDTO findAll() {
		LOGGER.info("find all subscriptions");
		return new SubscriptionListDTO(subscriptionRepository.findAll().stream()
				.map(SubscriptionMapper::subscriptionToSubscriptionDTO).collect(Collectors.toList()));
	}

	public SubscriptionDTO delete(SubscriptionDTO subscriptionDTO) {
		try {
			subscriptionRepository.deleteById(subscriptionDTO.getId());
			LOGGER.info("Subscription successfully deleted");
			return subscriptionDTO;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error deleting subscription", e);
			throw new ObjectNotFoundException(
					"Object not found Id: " + subscriptionDTO.getId() + ", type: " + Subscription.class.getName());
		}
	}

	public SubscriptionDTO create(SubscriptionDTO subscriptionDTO) {
		LOGGER.info("Create subscription: {}", subscriptionDTO);
		try {
			Subscription subscription = subscriptionRepository
					.save(SubscriptionMapper.subscriptionDTOToSubscription(subscriptionDTO));
			return SubscriptionMapper.subscriptionToSubscriptionDTO(subscription);
		} catch (DataIntegrityViolationException e) {
			LOGGER.error("user id not found", e);
			throw new DatabaseException("User id not found: " + subscriptionDTO.getUserId());
		}
	}

}
