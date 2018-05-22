package com.stackroute.maverick.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.omg.Messaging.SyncScopeHelper;
import org.springframework.stereotype.Service;

@Service
public class FileWriterReaderService {

	private BufferedWriter writer;
	private int result;

	public void writeFile(int gameId, int userId) throws IOException, ClassNotFoundException, NullPointerException {
		String gameIdAsString = new Integer(gameId).toString();
		String userIdAsString = new Integer(userId).toString();
		// String filePath = "file.txt";
		// HashMap<String, String> map = new HashMap<String, String>();
		//
		// String line;
		// BufferedReader reader = new BufferedReader(new FileReader(filePath));
		// while ((line = reader.readLine()) != null)
		// {
		// String[] parts = line.split("=", 2);
		// if (parts.length >= 2)
		// {
		// String key = parts[0];
		// String value = parts[1];
		// map.put(key, value);
		// } else {
		// System.out.println("ignoring line: " + line);
		// }
		// }
		//
		// for (String key : map.keySet())
		// {
		// System.out.println(key + "=" + map.get(key));
		//
		// if(key.equals(userIdAsString))
		// {
		// System.out.println("already exists");
		// }
		//
		// }
		// reader.close();
		//
		//

		// Initialising the writer
		//
		// Initialising the reader
		BufferedReader reader = new BufferedReader(new FileReader("file.txt"));

		// Set<Integer> playerSet = new HashSet<Integer>();
		// Map<Integer, Set<Integer>> gameQueue = new HashMap<Integer, Set<Integer>>();
		// playerSet.add(userId);
		// playerSet.add(2);
		// playerSet.add(3);
		// playerSet.add(4);

		// int gameId1 = 1;

		// writer.append(gameQueue.toString());
		// writer.append("\n");
		// writer.close();

		// Reading a file and putting it into a map

		Set<Integer> returnedPlayerSet = new HashSet<Integer>();
		Set<String> returnedPlayerSet1 = new HashSet<String>();
		// List<String> returnedPlayerSet1 = new ArrayList<String>();
		Map<Integer, Set<String>> returnedGameQueue = new HashMap<Integer, Set<String>>();
		String line;

		// writer = new BufferedWriter(new FileWriter("file.txt"));
		// returnedPlayerSet1.add(Integer.toString(userId));
		// returnedGameQueue.put(gameId, returnedPlayerSet1);
		// writer.append(returnedGameQueue.toString());
		// writer.append("\n");
		// writer.close();
		// System.out.println("First if loop");
		// System.out.println(returnedGameQueue);

		if ((line = reader.readLine()) == null) {

			System.out.println("For loop");
			writer = new BufferedWriter(new FileWriter("file.txt", true));
			// returnedPlayerSet1.add(Integer.toString(userId));
			// returnedGameQueue.put(gameId, returnedPlayerSet1);
			writer.append(userIdAsString);
			writer.append("\n");
			writer.close();
			System.out.println("First if loop");

		}

		
		// else
		// {
		// System.out.println("else loop");
		// {
		//
		// String[] parts = line.trim().split("=");
		// if (parts.length >= 2) {
		//
		// String key = parts[0].trim().split("\\{")[1].trim();
		// result = Integer.parseInt(key);
		//
		// returnedPlayerSet1.add(parts[1].trim().split("}")[0].trim().split("]")[0].trim().split("\\[")[1]
		// .trim().replace(" ", ""));
		// returnedGameQueue.put(result, returnedPlayerSet1);
		//
		// System.out.println("First read" + returnedGameQueue);
		//
		//
		//
		//
		// for(String key27 : returnedGameQueue.get(gameId)){
		//
		// if (userIdAsString.equals(key27)) {
		//
		// System.out.println("Already exists");
		//
		// } else {
		// System.out.println("Else loop");
		// System.out.println("userId" + userId);
		// System.out.println("userId" + gameId);
		// writer = new BufferedWriter(new FileWriter("file.txt"));
		// System.out.println("Second read" + returnedGameQueue);
		// System.out.println(returnedPlayerSet1);
		// String userIdtoAdd = userIdAsString.split(" ")[0].trim().replace(" ", "");
		// returnedPlayerSet1.add(userIdtoAdd.replaceAll(" ", ""));
		//
		// writer.write(returnedGameQueue.toString().replaceAll(" ", ""));
		//
		// writer.close();
		//
		//
		//// returnedPlayerSet1.add(Integer.toString(userId).trim());
		//// returnedGameQueue.put(gameId, returnedPlayerSet1).toString().trim();
		//// writer.append(returnedGameQueue.toString().trim());
		//// writer.append("\n");
		//// writer.close();
		// System.out.println("Last else loop" + returnedGameQueue);
		//
		// String[] parts1 = line.trim().split("=");
		// if (parts1.length >= 2) {
		//
		// String key1 = parts[0].trim().split("\\{")[1].trim();
		// result = Integer.parseInt(key1);
		//
		// returnedPlayerSet1.add(parts[1].trim().split("}")[0].trim().split("]")[0].trim().split("\\[")[1]
		// .trim().replace(" ", ""));
		// returnedGameQueue.put(result, returnedPlayerSet1);
		//
		// System.out.println("First read" + returnedGameQueue);
		//
		// }
		// }
		// }
		//
		// writer = new BufferedWriter(new FileWriter("file.txt"));
		// returnedPlayerSet1.add(Integer.toString(userId));
		// returnedGameQueue.put(gameId, returnedPlayerSet1);
		// writer.append(returnedGameQueue.toString());
		// writer.append("\n");
		// writer.close();
		//
		// }
		//
		// }
		//
		// }
	}
}
