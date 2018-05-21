package com.stackroute.maverick.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class AddPlayerServiceImpl implements AddPlayerService {

	public Map<Integer, Set<Integer>> addPlayertoQueue(int gameId, int userId) {
		
		Set<Integer> playerSet = new HashSet<Integer>();
		Map<Integer, Set<Integer>> gameQueue = new HashMap<Integer, Set<Integer>>();

		playerSet.add(userId);
		gameQueue.put(gameId, playerSet);

		// Set<Integer> playerSet1 = new HashSet<Integer>();
		// playerSet1.add(userId);
		// gameQueue.put(gameId,playerSet);

		// for ( int key : gameQueue.keySet() )
		// System.out.println("this" +key);
		//
		// Set<Integer> players = gameQueue.get(key);
		//// Condition to make sure one player is not returned

		// System.out.println("This is it " + players);
		return gameQueue;

	}

}
