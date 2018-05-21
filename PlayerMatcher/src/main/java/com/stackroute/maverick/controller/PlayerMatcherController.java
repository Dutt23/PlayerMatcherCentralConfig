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
import com.stackroute.maverick.service.AddPlayerService;

/**
 * Kafka Listener Controller class
 * 
 * @author shatayki
 *
 */

@Controller
public class PlayerMatcherController {
	/**
	 * Method to avoid bean error. As the domain isn't being stored in the database.
	 *
	 */
	@Bean
	public User user() {
		return new User();
	}

	/**
	 * Autowiring of the domian class
	 *
	 */
	@Autowired
	User user;

	/**
	 * Autowiring of the service class
	 */
	@Autowired
	AddPlayerService addPlayerService;

	/**
	 * 
	 * Method to add a player to a queue.
	 * 
	 * @param gameId
	 * @param userId
	 * @return
	 * @throws InterruptedException
	 */
	public Map<Integer, Set<Integer>> addPlayertoFastestFingerQueue(int gameId, int userId)
			throws InterruptedException {

		Map<Integer, Set<Integer>> gameQueue;
		// This is where the gameId will be stored
		gameQueue = addPlayerService.addPlayertoQueue(gameId, userId);
//		System.out.println("This is controller" + gameQueue);

		for (int key : gameQueue.keySet()) {

			Set<Integer> players = gameQueue.get(key);
			// Condition to make sure one player is not returned
			if (players.size() >= 2 || players.size() <= 4 && players.size() != 1) {

				return gameQueue;

			}

		}
		

		return null;

	}

}
