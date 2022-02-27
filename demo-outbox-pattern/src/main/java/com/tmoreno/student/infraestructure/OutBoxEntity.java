package com.tmoreno.student.infraestructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "outbox")
public class OutBoxEntity {
    @Id
    @Column(name = "event_id")
    private String eventId;

    @Column(name = "aggregate_id")
    private Integer aggregateId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "payload")
    private String payload;

    public OutBoxEntity() {

    }

    public OutBoxEntity(String eventId, Integer aggregateId, String eventName, String payload) {
        this.eventId = eventId;
        this.aggregateId = aggregateId;
        this.eventName = eventName;
        this.payload = payload;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Integer getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(Integer aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
