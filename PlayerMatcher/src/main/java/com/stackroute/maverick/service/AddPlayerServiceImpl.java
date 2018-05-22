package com.stackroute.maverick.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Service;

/**
 * Service for adding a player to a particular list
 * 
 * @author shatayki
 *
 */
@Service
public class AddPlayerServiceImpl implements AddPlayerService {
	// public static final Object monitor = new Object();

	/**
	 * Initialised variables required
	 * 
	 * @playerSet , Set used to store the userId.
	 * @gameQueue , map used to store the userId's and gameId's. With gameId as the
	 *            HashMap key
	 * @timer , initialising the timer to send the list , once the time expires.
	 */
	public static final Set<Integer> playerSet = new HashSet<Integer>();
	public static final Map<Integer, Set<Integer>> gameQueue = new HashMap<Integer, Set<Integer>>();
	public static Timer timer = new Timer();
	public static int gameId;

	// int k = 0;

	/**
	 * Method to add players to a given list. Using the gameId as the hashmap key
	 * Storing the array of userId's in a HashSet , and adding to the map
	 *
	 */
	public void addPlayertoQueue(int gameId, int userId) throws InterruptedException {

		// Set<Integer> playerSet = new HashSet<Integer>();
		// Map<Integer, Set<Integer>> gameQueue = new HashMap<Integer, Set<Integer>>();
		// gameQueue.put(gameId, playerSet);

		// Set<Integer> playerSet1 = new HashSet<Integer>();
		// playerSet1.add(userId);
		// gameQueue.put(gameId,playerSet);

		// for ( int key : gameQueue.keySet() )
		// System.out.println("this" +key);
		//
		// Set<Integer> players = gameQueue.get(key);
		//// Condition to make sure one player is not returned

		// while (playerSet.size() <= 4) {

		// This loop is entered when the first player is added.
		AddPlayerServiceImpl.gameId = gameId;

		if (playerSet.isEmpty()) {

			playerSet.add(userId);
			gameQueue.put(gameId, playerSet);
			timer = new Timer();
			timer.schedule(new RemindTask(), 5000);
			System.out.println("This if if" + playerSet);
		}
		// Entered when this is not the first player being added
		else {
			Iterator<Integer> it1 = gameQueue.keySet().iterator();
			while (it1.hasNext()) {

				int key = it1.next();

				System.out.println("Map Value:" + gameQueue.get(key));

				// Checking for duplicates

				if (key == userId) {

					System.out.println("Already exists");

				}

				else {

					playerSet.add(userId);
					gameQueue.put(gameId, playerSet);

					timer.schedule(new RemindTask(), 5000);

				}

			}
			System.out.println("Final map" + gameQueue);

		}
		// When max game queue size has been reached
		if (playerSet.size() == 4) {
			System.out.println("Greater than 4, safe to send");
			gameQueue.remove(gameId);
			playerSet.clear();
			timer.cancel();
			System.out.println(gameQueue);
		}

	}

	/**
	 * Class to send the list when time is up. And also cancel the timer
	 * 
	 * @author shatayki
	 *
	 */
	class RemindTask extends TimerTask {

		/**
		 * Method to send the list, once the given time is up.
		 * 
		 * @author shatayki
		 *
		 */
		public void run() {
			System.out.println("Time's up! List sent");
			
			gameQueue.remove(gameId);
			playerSet.clear();
			timer.cancel(); // Terminate the timer thread
		}

	}

}
