package com.wit.subscriptiondb.mapper;

import com.wit.subscriptiondb.domain.Subscription;
import com.wit.subscriptiondb.domain.User;
import com.wit.subscriptiondomain.SubscriptionDTO;

public interface SubscriptionMapper {
	
	public static SubscriptionDTO subscriptionToSubscriptionDTO(Subscription subscription) {
		SubscriptionDTO subDTO = new SubscriptionDTO();
		subDTO.setId(subscription.getId());
		subDTO.setPackageId(subscription.getPackageId());
		subDTO.setUserId(subscription.getUser().getId());
		subDTO.setDataSubscription(subscription.getDataSubscription());
		return subDTO;
	}
	
	public static Subscription subscriptionDTOToSubscription(SubscriptionDTO subscriptionDTO) {
		Subscription subscription = new Subscription();
		subscription.setId(subscriptionDTO.getId());
		subscription.setDataSubscription(subscriptionDTO.getDataSubscription());
		subscription.setPackageId(subscriptionDTO.getPackageId());
		subscription.setUser(new User(subscriptionDTO.getUserId()));
		return subscription;
	}
}
