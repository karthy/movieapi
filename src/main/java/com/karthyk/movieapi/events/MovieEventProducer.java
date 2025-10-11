package com.karthyk.movieapi.events;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieEventProducer {
    private final KafkaTemplate<String, MovieEvent> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    public void publish(MovieEvent evt) {
        kafkaTemplate.send(topic, evt.data().id(), evt);
    }
}

