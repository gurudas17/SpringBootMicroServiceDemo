package com.bookdemo.ServiceB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.bookdemo.ServiceB.repository.BookRepository;

@SpringBootApplication
@EnableEurekaClient
public class ServiceBApplication {

	@Autowired
	BookRepository bookRep;
	public static void main(String[] args) {
		SpringApplication.run(ServiceBApplication.class, args);
	}


	
}
