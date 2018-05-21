package com.stackroute.maverick.controllerunittest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.stackroute.maverick.controller.PlayerMatcherController;
import com.stackroute.maverick.service.AddPlayerService;
import com.stackroute.maverick.service.AddPlayerServiceImpl;

/**
 * Test cases for controller
 * 
 * @author shatayki
 *
 */
@RunWith(SpringRunner.class)
public class PlayerMatchedControllerUnitTest {
	/**
	 * Injecting mocks into the controller
	 */
	@InjectMocks
	PlayerMatcherController playerMatcherController;

	@MockBean
	AddPlayerService addPlayerService;

	private transient Set<Integer> expectedSet;

	private transient Map<Integer, Set<Integer>> expectedMap;

	/**
	 * Setting up the mock
	 */
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		expectedSet = new HashSet<Integer>();
		expectedMap = new HashMap<Integer, Set<Integer>>();
		expectedSet.add(13);
		expectedMap.put(1, expectedSet);
	}

	@After
	public void teardownMock() {
		expectedSet = null;
		expectedMap = null;
	}

	/**
	 * Test to add single player to the Queue
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddingSinglePlayertoSameQueue() throws Exception {
		// Initialising the result Map
		Map<Integer, Set<Integer>> actualMap = new HashMap<Integer, Set<Integer>>();
		
		// Mocking the service method
		Mockito.when(addPlayerService.addPlayertoQueue(1, 13)).thenReturn(expectedMap);
		actualMap = playerMatcherController.addPlayertoFastestFingerQueue(1, 13);
		verify(addPlayerService, times(1)).addPlayertoQueue(1, 13);
		

	}

	/**
	 * Failure to add duplicate valaues
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFailureAddingDuplicateValues() throws Exception {
		// Adding duplicate values to same game Id
		expectedSet.add(13);
		expectedSet.add(13);
		expectedMap.put(1, expectedSet);
		assertEquals(1,expectedMap.get(1).size());

	}

	/**
	 * Return Null only when one player is in the queue
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	public void testFailuretoFetchonlyOnePlayer() throws Exception {
		// Initialising the expected Map
		Map<Integer, Set<Integer>> actualMap = new HashMap<Integer, Set<Integer>>();
		// System.out.println("testing 2 2 3"+expectedMap);
		expectedMap.remove(1);
		// expectedSet.add(13);
		// Mocking the service method
		Mockito.when(addPlayerService.addPlayertoQueue(1, 13)).thenReturn(expectedMap);
		actualMap = playerMatcherController.addPlayertoFastestFingerQueue(1, 13);
		System.out.println("This is" + actualMap);
		verify(addPlayerService, times(1)).addPlayertoQueue(1, 13);
		assertNull(actualMap);

	}

	/**
	 * Test getting multiple players with the same game id
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMultiplePlayers() throws Exception {
		// Initialising the expected Map
		Map<Integer, Set<Integer>> actualMap = new HashMap<Integer, Set<Integer>>();
		expectedSet.add(14);
		expectedMap.put(1, expectedSet);

		// Mocking the service method
		Mockito.when(addPlayerService.addPlayertoQueue(1, 24)).thenReturn(expectedMap);
		actualMap = playerMatcherController.addPlayertoFastestFingerQueue(1, 24);
		System.out.println("This is" + actualMap);
		assertEquals(expectedMap, actualMap);

	}

	/**
	 * Test case to add a new set of UserId's with a different GameId
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddingSeperateQueue() throws Exception {
		// Initialising the expected Map
		Set<Integer> expectedSet1 = new HashSet<Integer>();
		Map<Integer, Set<Integer>> actualMap = new HashMap<Integer, Set<Integer>>();
		expectedSet1.add(23);
		expectedSet1.add(24);
		System.out.println(expectedSet);
		expectedMap.put(2, expectedSet1);
		System.out.println("Map" + expectedMap);
		// Mocking the service method
		Mockito.when(addPlayerService.addPlayertoQueue(2, 23)).thenReturn(expectedMap);
		actualMap = playerMatcherController.addPlayertoFastestFingerQueue(2, 23);
		System.out.println("This is" + actualMap);
		assertEquals(expectedMap, actualMap);

	}

	/**
	 * Fetching players with same game Id.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFetchingUserIdwithGameId() throws Exception {
		// Initialising the expected Map
		Set<Integer> expectedSet1 = new HashSet<Integer>();
		Map<Integer, Set<Integer>> actualMap = new HashMap<Integer, Set<Integer>>();
		expectedSet1.add(23);
		expectedSet1.add(24);
		expectedMap.put(2, expectedSet1);
		// Mocking the service method
		Mockito.when(addPlayerService.addPlayertoQueue(2, 23)).thenReturn(expectedMap);
		Set<Integer> expectedPlayers = expectedMap.get(2);
		actualMap = playerMatcherController.addPlayertoFastestFingerQueue(2, 23);
		Set<Integer> actualPlayers = actualMap.get(2);
		System.out.println("This is" + actualPlayers);
		assertEquals(expectedPlayers, actualPlayers);

	}
	
	/**
	 * Test case for writing into a file
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Test
	public void testWritinginFIle() throws ClassNotFoundException, IOException
	{
		playerMatcherController.writeFile();
		
	}

}
