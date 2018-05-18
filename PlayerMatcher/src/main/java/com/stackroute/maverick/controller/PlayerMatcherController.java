package com.stackroute.maverick.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;


@Controller
public class PlayerMatcherController {

	
	
	
	
	
	@KafkaListener(topics = "myTopic.t")
	public ResponseEntity<String> addPlayer(){
		
		return null;
	}
}
