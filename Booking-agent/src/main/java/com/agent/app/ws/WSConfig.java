package com.agent.app.ws;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WSConfig {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.agent.app.wsdl");
		return marshaller;
	}

	@Bean
	public WSFacilityClient backendMainClient(Jaxb2Marshaller marshaller) {
		WSFacilityClient client = new WSFacilityClient();
		client.setDefaultUri("http://localhost:1312/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public WSUserClient backendUserClient(Jaxb2Marshaller marshaller) {
		WSUserClient client = new WSUserClient();
		client.setDefaultUri("http://localhost:1312/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
