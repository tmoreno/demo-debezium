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
   "name": "student-outbox-connector",
   "config": {
      "connector.class": "io.debezium.connector.mysql.MySqlConnector",
      "database.hostname": "database",
      "database.port": "3306",
      "database.user": "debezium",
      "database.password": "dbz",
      "database.server.name": "production",
      "database.include.list": "students",
      "database.history.kafka.bootstrap.servers": "kafka:29092",  
      "database.history.kafka.topic": "studentsDbHistory", 
      "table.whitelist": "students.outbox",
      "tombstones.on.delete": "false",
      "transforms": "outbox",
      "transforms.outbox.type": "io.debezium.transforms.outbox.EventRouter",
      "transforms.outbox.table.field.event.id": "event_id",
      "transforms.outbox.table.field.event.key": "aggregate_id",
      "transforms.outbox.table.expand.json.payload": "true",
      "transforms.outbox.route.by.field": "event_name",
      "transforms.outbox.route.topic.replacement": "${routedByValue}"
   }
}'
```
