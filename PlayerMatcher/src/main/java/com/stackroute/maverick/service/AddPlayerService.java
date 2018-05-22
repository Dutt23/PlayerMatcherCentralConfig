package com.stackroute.maverick.service;

import java.util.Map;
import java.util.Set;

public interface AddPlayerService {
	
	public void addPlayertoQueue(int gameId , int uderId) throws InterruptedException;

}
