package com.karthyk.movieapi.controller;

import com.karthyk.movieapi.api.MovieRequest;
import com.karthyk.movieapi.api.MovieResponse;
import com.karthyk.movieapi.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Movies", description = "Movie CRUD endpoints")
@RestController
@RequestMapping("/v1/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @Operation(summary = "Create a movie")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponse create(@RequestBody MovieRequest req) {
        return movieService.create(req);
    }

    @Operation(summary = "Update a movie by ID")
    @PutMapping("/{id}")
    public MovieResponse update(@PathVariable UUID id, @RequestBody MovieRequest req) {
        return movieService.update(id, req);
    }

    @Operation(summary = "Delete a movie by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        movieService.delete(id);
    }
}

