Compose file attached is with two Broker and one replication factor

Replication Factor Explanation

In Kafka, topics are partitioned to allow for parallel processing and scalability. Each partition can be replicated across multiple brokers in the cluster. The replication factor determines how many copies, or replicas, of each partition are maintained in the cluster.

Here's a detailed explanation of the replication factor and its significance:

1. **Fault tolerance**: The primary purpose of replication is to ensure fault tolerance. By maintaining multiple replicas of each partition, Kafka can continue to serve data even if one or more brokers fail. If a broker hosting a partition goes down, Kafka can still serve data from the replicas hosted on other brokers.

2. **High availability**: Replicas allow Kafka to provide high availability by ensuring that data remains accessible even in the event of broker failures. Clients can continue to produce and consume messages without interruption, as Kafka automatically routes requests to available replicas.

3. **Durability**: Replication enhances data durability by providing redundancy. Even if one replica becomes unavailable due to hardware failure or maintenance, Kafka can still ensure that data is accessible from other replicas.

4. **Load balancing**: Kafka can distribute the load of producing and consuming messages across multiple brokers by utilizing replicas. This helps prevent hotspots and improves overall system performance and scalability.

5. **Consistency**: Kafka maintains consistency among replicas by ensuring that all replicas of a partition receive the same messages in the same order. This ensures data consistency across the cluster.

Here's an example to illustrate the concept of replication factor:

Let's say you have a Kafka topic named "orders" with a replication factor of 3 and 6 partitions. When you publish a message to the "orders" topic, Kafka distributes the message across the partitions based on the message key (if specified) or using a round-robin approach.

With a replication factor of 3:
- Kafka ensures that each of the 6 partitions has 3 replicas spread across different brokers in the cluster.
- If Broker A hosts partition 1, replicas of partition 1 will also be hosted on Broker B and Broker C.
- Similarly, if Broker D hosts partition 2, replicas of partition 2 will also be hosted on Broker E and Broker F.
- This ensures fault tolerance, high availability, and load balancing across the Kafka cluster.

In summary, the replication factor in Kafka determines the number of copies of each partition, providing fault tolerance, high availability, durability, load balancing, and consistency in the Kafka cluster.

To create Topics:
docker exec -it <kafka-container-id> kafka-topics --create --bootstrap-server localhost:9094 --replication-factor 1 --partitions 1 --topic test

kafka-console-consumer --bootstrap-server localhost:9094 --topic test --group group_id --from-beginning
kafka-console-consumer.sh \
--bootstrap-server localhost:9092 \
--topic test-topic \
--group group_id \

kafka-console-producer --bootstrap-server localhost:9094 --topic test
kafka-consumer-groups \
--bootstrap-server localhost:9092 \
--describe \
--group group_id

Docker Handy commands:

To get the container id use command 
docker ps

To Start interactive shell with docker
docker exec -it bash

Learning: 
Whenever creatig a topic always pass the partition it is very important, else consumer won't be assigned to any partition and you message will be lost