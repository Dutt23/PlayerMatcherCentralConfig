package com.stackroute.maverick.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Controller;

import com.stackroute.maverick.domain.User;
import com.stackroute.maverick.service.AddPlayerService;
import com.stackroute.maverick.service.FileWriterReaderService;

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
	@Autowired
	FileWriterReaderService fileWriterReaderService;

	/**
	 * 
	 * Method to add a player to a queue.
	 * 
	 * @param gameId
	 * @param userId
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void addPlayertoFastestFingerQueue(int gameId, int userId)
			throws InterruptedException, ClassNotFoundException, IOException {

		Map<Integer, Set<Integer>> gameQueue;
		// This is where the gameId will be stored
		addPlayerService.addPlayertoQueue(gameId, userId);
		// System.out.println("This is controller" + gameQueue);
		int i = 0;

		// for (int key : gameQueue.keySet()) {
		//
		// Set<Integer> players = gameQueue.get(key);
		// // Condition to make sure one player is not returned
		// if (players.size() >= 2 || players.size() <= 4 && players.size() != 1) {
		//
		//
		//
		// }
		//
		// }

	}

	public void display() {

	}

}
