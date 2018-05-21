package com.stackroute.maverick.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;


import com.stackroute.maverick.service.KafkaConsumerServiceImpl;
import com.stackroute.maverick.service.KafkaProducerServiceImpl;

/**
 * Configuration for the Consumer class of Kafka
 * 
 * @author shatayki
 *
 */
@Configuration
@EnableKafka
public class ReceiverConfig {

	/**
	 * Value for the server kafka is running on. Taken from the central config
	 */
	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;

	/**
	 * Used to configure the instance of the consumer.
	 * 
	 * @return
	 */
	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		// list of host:port pairs used for establishing the initial connections to the
		// Kafka cluster
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		// allows a pool of processes to divide the work of consuming and processing
		// records
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "gameEnginemultiplayer");
		// automatically reset the offset to the earliest offset
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		// deserializing the value
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		// adding all classes to deserialization
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return props;
	}

	// Working Version

	/**
	 * ConsumerFactory used to create an instance of a consumer
	 * 
	 * @return
	 */
	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	/**
	 * The kafkaListenerContainerFactory is responsible to create the listener
	 * container for a particular end point.
	 * 
	 * @return Kafka containers created by this condition.
	 */
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}
	
	
	// Version 1.0
	// @Bean
	// public ConsumerFactory<String, String> consumerFactory1() {
	// return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	// }
	// @Bean
	// public ConsumerFactory<String, Restaurant> consumerFactory1() {
	// return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new
	// StringDeserializer(),
	// new JsonDeserializer<>(Restaurant.class));
	// }
	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, Restaurant>
	// kafkaListenerContainerFactory1() {
	// ConcurrentKafkaListenerContainerFactory<String, Restaurant> factory =
	// new ConcurrentKafkaListenerContainerFactory<>();
	// factory.setConsumerFactory(consumerFactory1());
	//
	// return factory;
	// }

	// @Bean
	// public
	// KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,
	// String>> kafkaListenerContainerFactory1() {
	// ConcurrentKafkaListenerContainerFactory<String, String> factory =
	// new ConcurrentKafkaListenerContainerFactory<>();
	// factory.setConsumerFactory(consumerFactory1());
	//
	//
	// return factory;
	// }
	
	

}