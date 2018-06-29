package com.agent.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class BookingAgentApplication {
	
//	static {
//	    //for localhost testing only
//	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
//	    new javax.net.ssl.HostnameVerifier(){
//
//	        public boolean verify(String hostname,
//	                javax.net.ssl.SSLSession sslSession) {
////	            if (hostname.equals("localhost")) {
////	                return true;
////	            }
//	            return true;
//	        }
//	    });
//	}

	public static void main(String[] args) {
		SpringApplication.run(BookingAgentApplication.class, args);
	}
	
	@Bean 
	public RequestContextListener requestContextListener(){
	    return new RequestContextListener();
	} 
}
