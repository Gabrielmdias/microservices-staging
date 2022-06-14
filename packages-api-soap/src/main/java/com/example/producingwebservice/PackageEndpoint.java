package com.example.producingwebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetPackagesRequest;
import io.spring.guides.gs_producing_web_service.GetPackagesResponse;

@Endpoint
public class PackageEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private PackageRepository packageRepository;

	@Autowired
	public PackageEndpoint(PackageRepository packageRepository) {
		this.packageRepository = packageRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPackagesRequest")
	@ResponsePayload
	public GetPackagesResponse getCountry(@RequestPayload GetPackagesRequest request) {
		GetPackagesResponse response = new GetPackagesResponse();
		response.getPackages().addAll(packageRepository.findPackages());
		return response;
	}
}
