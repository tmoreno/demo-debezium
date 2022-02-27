### Run
```shell
docker-compose up -d
```
### Linking the Debezium Kafka Connect With the Outbox Table
```shell
curl -X POST \
  http://localhost:8083/connectors/ \
  -H 'content-type: application/json' \
  -d '{
   "name": "debezium-connector",
   "config": {
      "connector.class": "io.debezium.connector.mysql.MySqlConnector",
      "tasks.max": "1", 
      "database.hostname": "database",
      "database.port": "3306",
      "database.user": "debezium",
      "database.password": "dbz",
      "database.server.name": "production",
      "database.include.list": "students",
      "database.history.kafka.bootstrap.servers": "kafka:29092",  
      "database.history.kafka.topic": "studentsDbHistory"
   }
}'
```
