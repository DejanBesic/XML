package com.admin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAsync
@EnableScheduling
@EnableTransactionManagement	
public class BookingAdminApplication {	
	
	public static void main(String[] args) {
		SpringApplication.run(BookingAdminApplication.class, args);
	}
}
