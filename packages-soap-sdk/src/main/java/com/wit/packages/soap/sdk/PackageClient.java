
package com.wit.packages.soap.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.wit.packages.soap.sdk.wsdl.GetPackagesRequest;
import com.wit.packages.soap.sdk.wsdl.GetPackagesResponse;

public class PackageClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(PackageClient.class);

	public GetPackagesResponse getPackages() {

		GetPackagesRequest request = new GetPackagesRequest();

		log.info("Requesting location for packages");

		GetPackagesResponse response = (GetPackagesResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8081/ws/packages", request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetPackagesRequest"));

		return response;
	}

}
