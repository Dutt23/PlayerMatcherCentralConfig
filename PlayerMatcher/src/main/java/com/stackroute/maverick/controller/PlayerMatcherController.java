package com.stackroute.maverick.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/v1/matching")
public class PlayerMatcherController {

	
//	public ResponseEntity<String> greetingForYou() {
//		return new ResponseEntity<String>("Hi welcome to the maverick app", HttpStatus.OK);
//	}
	
	
	
	
	public ResponseEntity<String> addPlayer(@PathVariable(value = "userId") int userId){
		
		return null;
	}
}
