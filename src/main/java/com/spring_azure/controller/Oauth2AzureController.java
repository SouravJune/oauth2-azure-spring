package com.spring_azure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2AzureController {

	@GetMapping("/welcome")
	public String welcome() {
		return "You successfully logged in azure directory"; 
	}
}