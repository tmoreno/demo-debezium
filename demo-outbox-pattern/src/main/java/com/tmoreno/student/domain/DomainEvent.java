package com.tmoreno.student.domain;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public final class DomainEvent {
    private final UUID id;
    private final String name;
    private final int aggregateId;
    private final Map<String, Object> data;
    private final Instant createdOn;

    public DomainEvent(String name, int aggregateId, Map<String, Object> data) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.aggregateId = aggregateId;
        this.data = data;
        this.createdOn = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAggregateId() {
        return aggregateId;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }
}
