package com.amela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class nameController {

	@RequestMapping("/name")
	public String name() {
		
		return "Vinh";
	}
}
