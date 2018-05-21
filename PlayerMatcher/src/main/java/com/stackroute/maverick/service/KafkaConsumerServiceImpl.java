package com.stackroute.maverick.service;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.stackroute.maverick.domain.User;

/**
 * Implementation of Kafka Consumer interface
 * 
 * @author shatayki
 *
 */
@Service
public class KafkaConsumerServiceImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	
	/**
	 * Listener method for fastest finger first
	 * Payload is the object sent by game manager, containing gameId and userId
	 * @param payload
	 * 
	 */
	@KafkaListener(topics = "gameEnginemultiplayer.t")
	public void kafkaConnsumerFastestFinger(User payload) {
		LOGGER.info("received payload='{}'", payload.toString());
		latch.countDown();
	}

}
