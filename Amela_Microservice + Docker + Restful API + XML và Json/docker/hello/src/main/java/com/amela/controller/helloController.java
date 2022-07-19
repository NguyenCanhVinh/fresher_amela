package com.amela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amela.service.helloService;

@RestController
public class helloController {
	
	@Autowired
	helloService helloService;

	//http://localhost:8081/hello
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello";
	}
}
