package com.wit.subscriptionfacade.configs;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.wit.subscriptiondomain.PackageDTO;

@Component
public class Cache {
	
	private static final String PACKAGES = "packages";
	
	private ClientConfig clientConfig = new ClientConfig();
	
	private HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
	
	public void addPackage(PackageDTO packageDto) {
		List<PackageDTO> packages = client.getList(PACKAGES);
		packages.add(packageDto);
	}
	
	public void addPackage(List<PackageDTO> packagesDto) {
		List<PackageDTO> packages = client.getList(PACKAGES);
		packages.addAll(packagesDto);
	}
	
	public List<PackageDTO> getPackages() {
		return client.getList(PACKAGES);
	}
}
