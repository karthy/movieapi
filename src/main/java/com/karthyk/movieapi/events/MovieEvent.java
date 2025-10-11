package com.karthyk.movieapi.events;

import lombok.Builder;

import java.time.Instant;

@Builder
public record MovieEvent(
        String eventType,   // MOVIE_CREATED | MOVIE_UPDATED | MOVIE_DELETED
        String version,     // v1
        Instant occurredAt,
        Payload data
) {
    @Builder
    public record Payload(String id, String title, String genre, Integer year) {}
}

