package com.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kafka.constants.Constants;

public class KafkaConsumer {

	@KafkaListener(topics = { Constants.KAFKA_TOPIC_01, Constants.KAFKA_TOPIC_02, Constants.KAFKA_TOPIC_03 })
	public void receive(Object obj) {
		System.out.println(getJson(obj));
	}

	public JsonObject getJson(Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		JsonElement jElement = new JsonParser().parse(json);
		JsonObject jobject = jElement.getAsJsonObject();
		return jobject.getAsJsonObject("value");
	}

}
