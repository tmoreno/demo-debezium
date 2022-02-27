package com.tmoreno.student.infraestructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmoreno.student.domain.DomainEvent;
import com.tmoreno.student.domain.OutBoxRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DomainEventListener {

    private final OutBoxRepository outBoxRepository;
    private final ObjectMapper objectMapper;

    public DomainEventListener(OutBoxRepository outBoxRepository, ObjectMapper objectMapper) {
        this.outBoxRepository = outBoxRepository;
        this.objectMapper = objectMapper;
    }

    @EventListener
    public void listen(DomainEvent event) throws JsonProcessingException {
        OutBoxEntity entity = new OutBoxEntity(
            event.getId().toString(),
            event.getAggregateId(),
            event.getName(),
            objectMapper.writeValueAsString(event)
        );

        outBoxRepository.save(entity);

        /*
         * Delete the event once written, so that the outbox doesn't grow.
         * The CDC eventing polls the database log entry and not the table in the database.
         */
        outBoxRepository.delete(entity);
    }
}
