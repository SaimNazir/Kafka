package io.conduktor.demos.kafka;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {

    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());
    public static void main(String[] args) {
        log.info("hello kafka");

        // Create producer properties
        Properties properties = new Properties();

        // Connect to Localhost
        //properties.setProperty("bootstrap.servers", "127.0.0.1.9092");

        // Connect to Conduktor
        

        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"4WsAxVETPJqhFvSgxzqhaD\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiI0V3NBeFZFVFBKcWhGdlNneHpxaGFEIiwib3JnYW5pemF0aW9uSWQiOjcyMjM4LCJ1c2VySWQiOjgzODYyLCJmb3JFeHBpcmF0aW9uQ2hlY2siOiJhZjc1MzZjOS03NGJlLTRmNGItODdkNy03ZTY3OGM2M2YxYmQifX0.Rmq6RXRbJmHmNRoVJMa6E0nGB2qPx2YStSJA0KYGM7A\";");
        properties.setProperty("sasl.mechanism", "PLAIN");
        properties.setProperty("bootstrap.servers", "cluster.playground.cdkt.io:9092");



        // Set producer properties. Will serialize string data
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());



        // Create producer, is a Kafka producer where key and value are both of type string
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // Create producer record
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("my_topic", "Hello Kafka");

        // Send data
        producer.send(producerRecord);

        // Flush and close the producer
        producer.flush();
        producer.close(); //.close() also flushes before closing
    }
}