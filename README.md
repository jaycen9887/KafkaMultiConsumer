# KafkaMultiConsumer
Consume from multiple topics all at once.

This application will continuously listen to the kafka topics specified. 

This application also listens for the exact data listed in the User model class. If you wish to listen to different data, create your own model and update the types inside the KafkaConsumerConfig class

  - Remove the User model listed in these methods in the KafkaConsumerConfig:
    -- public ConsumerFactory<String, User> consumerFactory()
    -- public ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory()
    
Be sure to update the Constants class with your Kafka Setup information.

Default is three topics, however you can add more. 

If you add additional topics, be sure to update the Constants class with your topics, and also update the Listener inside the KafkaConsumer class
  @KafkaListener(topics = { Constants.KAFKA_TOPIC_01, Constants.KAFKA_TOPIC_02, Constants.KAFKA_TOPIC_03 })
  
  Add your topics inside the curly brackets (seperated by commas).
  
This is also setup for SSL connection, in the Constants class, add:
  Location of your Keystore and Truststore.
  Passwords for the Keystore, Truststore, and KeyPair
  List of Brokers (Seperated By Commas)
  

