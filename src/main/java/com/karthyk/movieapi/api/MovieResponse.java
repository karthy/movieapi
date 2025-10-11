package com.karthyk.movieapi.api;

public record MovieResponse(
        String id, String title, String genre, Integer year
) {}
