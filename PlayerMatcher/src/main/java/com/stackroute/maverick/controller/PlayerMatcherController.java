package com.stackroute.maverick.controller;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import com.stackroute.maverick.domain.User;


@Controller
public class PlayerMatcherController {
	
	@Bean
	public User user() {
		return new User();
	}
	
	@Autowired
	User user;
    
	
	public ResponseEntity<String> addPlayer(){
		Set<String> set = new HashSet<String>();
		Map<Integer,Set<String>> map = new HashMap<Integer,Set<String>>();
		set.add("ABC");
	    set.add("DEF");
	    map.put(123,set);
		return null;
	}
}
