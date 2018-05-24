package com.stackroute.maverick.kafkaunittest;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.maverick.domain.User;
import com.stackroute.maverick.service.KafkaConsumerServiceImpl;
import com.stackroute.maverick.service.KafkaProducerServiceImpl;

/**
 * Unit test for kafka
 * 
 * @author shatayki
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class KafkaUnitTest {

	/**
	 * AUtowiring instance of producer class
	 */
	@Autowired
	KafkaProducerServiceImpl kafkaProducerService;

	/**
	 * Autowiring instance of consumer class
	 */
	@Autowired
	KafkaConsumerServiceImpl kafkaConsumerService;

	/**
	 * Creates the necessary MessageListenerContainer instances for the registered endpoints. Also manages the lifecycle of the listener containers, 
	 * in particular within the lifecycle of the application context.
	 */
	@Autowired
	private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

	/**
	 * Create embedded Kafka brokers.
	 * @param 1st field the number of brokers.
	 * @param 2nd field passed into TestUtils.createBrokerConfig.
	 * @param 3rd field the topics to create (2 partitions per).
	 * A JUnit @ClassRule is provided that creates an embedded Kafka and an embedded Zookeeper server.
	 */
	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "gameEnginemultiplayer.t");

	/**
	 * setting up the kafka server.
	 * Embedded kafka starts on a different port each time.
	 * Getting broker properties
	 * @1st param , Name of the property.
	 * @2nd param , Value of the property
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
	}

	@Before
	public void setUp() throws Exception {
		// wait until the partitions are assigned
		for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
				.getListenerContainers()) {
			ContainerTestUtils.waitForAssignment(messageListenerContainer,

					embeddedKafka.getPartitionsPerTopic());
			
		}
	}

	@Test
	public void testReceive() throws InterruptedException {
		User user = new User();
		user.getName();
		kafkaProducerService.sendFastestFingerPlayerList("gameEnginemultiplayer.t", user);
		kafkaConsumerService.getLatch().await(10000, TimeUnit.MILLISECONDS);
	        assertEquals(kafkaConsumerService.getLatch().getCount(), 0);

	}

}
