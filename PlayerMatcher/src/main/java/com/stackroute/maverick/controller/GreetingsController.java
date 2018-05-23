package com.stackroute.maverick.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingsController {

	@GetMapping("/a")
	public ResponseEntity<String> greetingForYou() {
		return new ResponseEntity<String>("Hi welcome to the maverick app", HttpStatus.OK);
	}
}
