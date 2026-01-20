# Payment REST Service

This Spring Boot project provides a REST API to accept payments and publishes payment events to Kafka.

## Prerequisites

- Java 17 installed
- Maven installed
- Kafka 3.9.1 installed

## Step 1: Download & setup Kafka

https://kafka.apache.org/community/downloads/ or

```bash
wget https://downloads.apache.org/kafka/3.9.1/kafka_2.13-3.9.1.tgz
tar -xzf kafka_2.13-3.9.1.tgz

mv kafka_2.13-3.9.1 kafka
## Kafka folder now contains bin/ and config/ directories.
```

## Step 2: Start Zookeeper and Kafka broker

Kafka uses Zookeeper for local development

### Start Zookeeper (terminal 1):
```bash
cd ~/kafka
bin/zookeeper-server-start.sh config/zookeeper.properties
```

### Start Kafka broker (terminal 2):
```bash
cd ~/kafka
bin/kafka-server-start.sh config/server.properties
```

## Step 3: Create Kafka topic
```bash
cd ~/kafka
bin/kafka-topics.sh --create \
--topic payments-topic \
--bootstrap-server localhost:9092 \
--partitions 3 \
--replication-factor 1
```

#### Verify:
```bash
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```
Output:
```bash
payments-topic
```
## Step 4: Start a Kafka consumer (for testing)

Open a new terminal:
```bash
cd ~/kafka
bin/kafka-console-consumer.sh \
--topic payments-topic \
--bootstrap-server localhost:9092 \
--from-beginning
```

This will print all messages published to the topic.

## Step 5: In the Spring Boot app, update the properties

```properties
topics.payment-requested=payments-topic
spring.kafka.bootstrap-servers=localhost:9092
```