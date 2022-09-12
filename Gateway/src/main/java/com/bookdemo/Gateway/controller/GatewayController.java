package com.bookdemo.Gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GatewayController {

	@Autowired
	Environment env;
	
	@GetMapping("/test")

	public ResponseEntity<String> checkStatus()
	{

		log.debug(" Sucessfully inovke " +env.getProperty("spring.application.name"));
		return new ResponseEntity<String>("Sucess "+env.getProperty("spring.application.name"), HttpStatus.OK);
		
	}
}
