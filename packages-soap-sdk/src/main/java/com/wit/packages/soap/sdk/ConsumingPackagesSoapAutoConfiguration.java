
package com.wit.packages.soap.sdk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ConsumingPackagesSoapAutoConfiguration {
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.wit.packages.soap.sdk.wsdl");
		return marshaller;
	}

	@Bean
	public PackageClient packageClient(Jaxb2Marshaller marshaller) {
		PackageClient client = new PackageClient();
		client.setDefaultUri("http://localhost:8081/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
