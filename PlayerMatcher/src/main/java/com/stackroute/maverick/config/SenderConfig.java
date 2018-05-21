package com.stackroute.maverick.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import org.springframework.kafka.support.serializer.JsonSerializer;


import com.stackroute.maverick.service.KafkaProducerServiceImpl;

/**
 * Kafka Producer Config class.
 * Configuration of the producer 
 * @author shatayki
 *
 */
@Configuration
public class SenderConfig {

	/**
	 * Value for the server kafka is running on.
	 * Taken from the central config
	 */
	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;

	/**
	 * Storing the producer properties in a hashmap.
	 * And configuring the producer class.
	 * Used to configure instance of producer
	 * @return
	 */
	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return props;
	}

	/**
	 * Getting the template for producing
	 * @return
	 */
	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	/**
	 * Configuring the kafka template to send String values
	 * @return
	 */
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	/**
	 * Getting the kafka template
	 * @return
	 */
	@Bean
	public ProducerFactory<String, Object> producerFactory1() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	/**
	 * Configuring the kafka template to send Objects
	 * @return
	 */
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate1() {
		return new KafkaTemplate<>(producerFactory1());
	}
	
//	@Bean
//	public KafkaProducerServiceImpl kafkaProducerService() {
//		return new KafkaProducerServiceImpl();
//	}
}
