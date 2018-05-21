package com.stackroute.maverick.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
		// System.out.println("This is controller" + gameQueue);

		for (int key : gameQueue.keySet()) {

			Set<Integer> players = gameQueue.get(key);
			// Condition to make sure one player is not returned
			if (players.size() >= 2 || players.size() <= 4 && players.size() != 1) {

				return gameQueue;

			}

		}

		return null;

	}

	
	public void writeFile() throws IOException, ClassNotFoundException {
		Set<String> playerSet = new HashSet<String>();
		Map<Integer, Set<String>> gameQueue = new HashMap<Integer, Set<String>>();
		playerSet.add("a");
		playerSet.add("b");
		playerSet.add("c");
		gameQueue.put(1, playerSet);
		System.out.println(gameQueue);

		BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt", true));
		writer.append(gameQueue.toString());
		writer.append("\n");
		writer.close();

		// ObjectOutputStream oos = new ObjectOutputStream(new
		// FileOutputStream("file.txt",true));
		// oos.writeObject(gameQueue.toString());
		//
		// oos.flush();
		// oos.close();
		System.out.println("File written");

		
//		 Reading a file and putting it into a map
		String filePath = "file.txt";
		Set<String> returnedPlayerSet = new HashSet<String>();
		Map<String, Set<String>> returnedGameQueue = new HashMap<String, Set<String>>();
		String line;
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split("=");
			if (parts.length >= 2) {
				String key = parts[0].split("\\{")[1];
				returnedPlayerSet.add(parts[1].split("}")[0].split("]")[0].split("\\[")[1]);
				System.out.println("This is key " + key);
				System.out.println(parts[1]);
				returnedGameQueue.put(key, returnedPlayerSet);
			} else {
				System.out.println("ignoring line: " + line);
			}
		}

		for (String key : returnedGameQueue.keySet()) {
			System.out.println(key + "=" + returnedGameQueue.get(key));
			System.out.println(returnedGameQueue);
		}
		reader.close();

		// ObjectInputStream ois = new ObjectInputStream(new
		// FileInputStream("file.txt"));
		// HashMap<Integer, Set<String>> returnedMap = (HashMap<Integer,
		// Set<String>>)ois.readObject();
		//
		// Use returned object.
		// System.out.println(returnedMap);
		// ois.close();

		// BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt",true));
		// writer.append(fileObj.toString());
		// writer.close();
		// BufferedReader r = new BufferedReader( new FileReader( "file.txt" ) );
		// String s = "", line = null;
		// while ((line = r.readLine()) != null) {
		//
		// s += line;
		//
		// }
		// System.out.print("Reading file " + s);

	}

	// public void readFile() throws IOException {
	//
	//
	// BufferedReader r = new BufferedReader( new FileReader( "file.txt" ) );
	// String s = "", line = null;
	// while ((line = r.readLine()) != null) {
	// s += line;
	// }
	// System.out.print("Reading file " + s);
	// }

}
