package com.karthyk.movieapi.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieRequest(
        @NotBlank String title,
        @NotBlank String genre,
        @NotNull Integer year
) {}

