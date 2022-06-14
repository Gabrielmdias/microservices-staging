package com.wit.subscriptionfacade.subscription.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.wit.subscriptiondomain.Operation;
import com.wit.subscriptiondomain.PackageDTO;
import com.wit.subscriptiondomain.SubscriptionDTO;
import com.wit.subscriptiondomain.SubscriptionListDTO;
import com.wit.subscriptionfacade.exceptions.LoyaltyPeriodNotReachedException;
import com.wit.subscriptionfacade.packages.service.PackageService;
import com.wit.subscriptionfacade.service.SendRequestService;

@Service
public class SubscriptionService {

	private SendRequestService sendRequestService;
	private PackageService packageService;

	public SubscriptionService(SendRequestService sendRequestService, PackageService packageService) {
		this.sendRequestService = sendRequestService;
		this.packageService = packageService;
	}

	private boolean checkUnsubscribe(UUID id) {
		SubscriptionDTO subDTO = findByIdSubscription(id);
		long diffInMillies = Math.abs(new Date().getTime() - subDTO.getDataSubscription().getTime());
		long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		PackageDTO packageDto = packageService.findByIdPackage(subDTO.getPackageId());
		return days >= packageDto.getLoyaltyPeriod();
	}

	public SubscriptionDTO findByIdSubscription(UUID id) {
		SubscriptionDTO subDTO = new SubscriptionDTO();
		subDTO.setOperacao(Operation.FIND_BY_ID);
		subDTO.setId(id);
		return (SubscriptionDTO) sendRequestService.sendSubscriptionMessage(subDTO);
	}

	public void delete(UUID id) {
		if (checkUnsubscribe(id)) {
			SubscriptionDTO subDTO = new SubscriptionDTO();
			subDTO.setId(id);
			subDTO.setOperacao(Operation.DELETE);
			sendRequestService.sendSubscriptionMessage(subDTO);
		} else {
			throw new LoyaltyPeriodNotReachedException("Cannot unsubscribe, as you have reached the minimum time");
		}
	}

	public SubscriptionDTO createSubscription(Integer id, UUID userId) {
		SubscriptionDTO subDTO = new SubscriptionDTO();
		packageService.findByIdPackage(id);
		subDTO.setPackageId(id);
		subDTO.setOperacao(Operation.CREATE);
		subDTO.setUserId(userId);
		return (SubscriptionDTO) sendRequestService.sendSubscriptionMessage(subDTO);
	}
	
	public List<SubscriptionDTO> findAll() {
		SubscriptionDTO subDTO = new SubscriptionDTO();
		subDTO.setOperacao(Operation.FIND_ALL);
		SubscriptionListDTO resp = (SubscriptionListDTO) sendRequestService.sendSubscriptionMessage(subDTO);
		return resp.getSubscriptionsDto();
	}

}
