package com.kafka.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.kafka.constants.Constants;
import com.kafka.consumer.KafkaConsumer;
import com.kafka.models.User;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Bean
	public Map<String, Object> consumerConfigs() throws IOException {
		Map<String, Object> props = new HashMap<>();

		if (Constants.DEV) {
			props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_SERVER_LIST);
			props.put("security.protocol", Constants.KAFKA_PROTOCOL);
			props.put(SslConfigs.SSL_PROTOCOL_CONFIG, Constants.SSL_PROTOCOL);

			props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, Constants.TRUSTSTORE_LOCATION);
			props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, Constants.TRUSTSTORE_PASSWORD);
			
			props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, Constants.KEYSTORE_LOCATION);
			props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, Constants.KEYSTORE_PASSWORD);
			props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, Constants.KEY_PASSWORD);
		} else {
			props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_SERVER);
		}
		props.put(ConsumerConfig.GROUP_ID_CONFIG, Constants.GROUP_ID);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, Constants.OFFSET_RESET);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Constants.AUTO_COMMIT_CONFIG);

		return props;
	}

	@Bean
	public ConsumerFactory<String, User> consumerFactory() throws IOException {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
				new JsonDeserializer<>(User.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory() throws IOException {
		ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setErrorHandler(new KafkaErrorHandler());
		return factory;
	}

	@Bean
	public KafkaConsumer receiver() {
		return new KafkaConsumer();
	}
}
