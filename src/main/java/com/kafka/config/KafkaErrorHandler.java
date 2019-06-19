package com.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ErrorHandler;

public class KafkaErrorHandler implements ErrorHandler {
	static final Logger logger = LoggerFactory.getLogger(KafkaErrorHandler.class);

	@Override
	public void handle(Exception arg0, ConsumerRecord<?, ?> arg1) {
		if (arg1 != null) {
			logger.debug("Kafka Error Message Details. Topic: " + arg1.topic(),
					" Key: " + arg1.key() + " Value: " + arg1.value());
		}
	}
}
