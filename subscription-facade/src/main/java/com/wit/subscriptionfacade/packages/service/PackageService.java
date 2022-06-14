package com.wit.subscriptionfacade.packages.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wit.packages.soap.sdk.PackageClient;
import com.wit.subscriptiondomain.PackageDTO;
import com.wit.subscriptiondomain.exceptions.ObjectNotFoundException;
import com.wit.subscriptionfacade.configs.Cache;

@Service
public class PackageService {
	
	private Cache cache;
	private PackageClient packageClient;

	public PackageService(Cache cache, PackageClient packageClient) {
		this.cache = cache;
		this.packageClient = packageClient;
	}
	
	public PackageDTO findByIdPackage(int id) {
		Optional<PackageDTO> packageDto = getPackages().stream()
				.filter(p -> p.getId() == id).findFirst();
		if (packageDto.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto nao encontrado Id: " + id + ", Tipo: " + PackageDTO.class.getName());
		}
		return packageDto.get();
	}
	
	public List<PackageDTO> getPackages() {
		if (cache.getPackages().isEmpty()) {
			packageClient.getPackages().getPackages().forEach(p -> {
				PackageDTO packageDto = new PackageDTO(p);
				cache.addPackage(packageDto);
			});
		}
		return cache.getPackages();
	}

}
