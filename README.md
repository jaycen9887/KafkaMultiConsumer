# KafkaMultiConsumer
Consume from multiple topics all at once.

This application will continuously listne to the kafka topics specified. 

Be sure to update the Constants class with your Kafka Setup information.

Default is three topics, however you can add more. 

Update the Constants class with your topics, and also update the Listener inside the KafkaConsumer class
  @KafkaListener(topics = { Constants.KAFKA_TOPIC_01, Constants.KAFKA_TOPIC_02, Constants.KAFKA_TOPIC_03 })
  
  Add your topics inside the curly brackets (seperated by commas).
  
This is also setup for SSL connection, in the Constants class, add:
  Location of your Keystore and Truststore.
  Passwords for the Keystore, Truststore, and KeyPair
  List of Brokers (Seperated By Commas)
  


