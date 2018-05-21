package com.stackroute.maverick.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.stackroute.maverick.domain.User;

/**
 * Implementation of Kafka Producer method
 * 
 * @author shatayki
 *
 */
@Service
public class KafkaProducerServiceImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplateFastestFinger;

	/**
	 * Kafka producer method for the mupltiplayer fastest finger first
	 * 
	 *
	 */
	public void sendFastestFingerPlayerList(String topic, User payload) {
		LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
		kafkaTemplateFastestFinger.send(topic, payload);
	}

}
