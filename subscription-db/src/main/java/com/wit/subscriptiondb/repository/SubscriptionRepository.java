package com.wit.subscriptiondb.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wit.subscriptiondb.domain.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

}
