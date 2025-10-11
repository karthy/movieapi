package com.karthyk.movieapi.config;

import com.karthyk.movieapi.events.MovieEvent;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, MovieEvent> movieEventConsumerFactory(org.springframework.boot.autoconfigure.kafka.KafkaProperties props) {
        var config = props.buildConsumerProperties();
        // ensure value deserializer targets MovieEvent
        var value = new JsonDeserializer<>(MovieEvent.class);
        value.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), value);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MovieEvent> movieEventListenerFactory(
            ConsumerFactory<String, MovieEvent> movieEventConsumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, MovieEvent>();
        factory.setConsumerFactory(movieEventConsumerFactory);
        return factory;
    }
}

