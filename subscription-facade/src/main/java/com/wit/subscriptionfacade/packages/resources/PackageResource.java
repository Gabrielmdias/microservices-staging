package com.wit.subscriptionfacade.packages.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wit.subscriptiondomain.PackageDTO;
import com.wit.subscriptionfacade.packages.service.PackageService;

@RestController
@RequestMapping(value = "/packages")
public class PackageResource {
	
	private PackageService packageService;
	
	public PackageResource(PackageService packageService) {
		this.packageService = packageService;
	}
	
	@GetMapping()
	public ResponseEntity<List<PackageDTO>> listPackages() {
		List<PackageDTO> list = packageService.getPackages();
		return ResponseEntity.ok().body(list);
	}
	
}
