package com.stackroute.maverick.service;

import java.util.Map;
import java.util.Set;

public interface AddPlayerService {
	
	public Map<Integer, Set<Integer>>  addPlayertoQueue(int gameId , int uderId);

}
