package com.karthyk.movieapi.events;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MovieEventListener {

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onEvent(@Payload MovieEvent evt) {
        System.out.printf("🎬 [Listener] %s -> %s (%s)%n",
                evt.eventType(), evt.data().title(), evt.data().id());
        // TODO: update a local audit table, cache, or just log.
    }
}

