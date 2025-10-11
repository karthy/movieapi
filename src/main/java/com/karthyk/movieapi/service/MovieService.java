package com.karthyk.movieapi.service;

import com.karthyk.movieapi.api.MovieRequest;
import com.karthyk.movieapi.api.MovieResponse;
import com.karthyk.movieapi.entity.Movie;
import com.karthyk.movieapi.events.MovieEvent;
import com.karthyk.movieapi.events.MovieEventProducer;
import com.karthyk.movieapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository repo;
    private final MovieEventProducer producer;

    public MovieResponse create(MovieRequest req) {
        Movie m = repo.save(Movie.builder()
                .title(req.title())
                .genre(req.genre())
                .year(req.year())
                .build());

        emit("MOVIE_CREATED", m);
        return map(m);
    }

    public MovieResponse update(UUID id, MovieRequest req) {
        Movie m = repo.findById(id).orElseThrow();
        m.setTitle(req.title());
        m.setGenre(req.genre());
        m.setYear(req.year());
        m = repo.save(m);
        emit("MOVIE_UPDATED", m);
        return map(m);
    }

    public void delete(UUID id) {
        Movie m = repo.findById(id).orElseThrow();
        repo.delete(m);
        emit("MOVIE_DELETED", m);
    }

    private void emit(String type, Movie m) {
        var payload = MovieEvent.Payload.builder()
                .id(m.getId().toString())
                .title(m.getTitle())
                .genre(m.getGenre())
                .year(m.getYear())
                .build();

        var event = MovieEvent.builder()
                .eventType(type)
                .version("v1")
                .occurredAt(Instant.now())
                .data(payload)
                .build();

        producer.publish(event);
    }

    private MovieResponse map(Movie m) {
        return new MovieResponse(
                m.getId().toString(), m.getTitle(), m.getGenre(), m.getYear()
        );
    }

    public List<MovieResponse> getAllMovies() {
        return repo.findAll()
                .stream()
                .map(m -> new MovieResponse(
                        m.getId().toString(),
                        m.getTitle(),
                        m.getGenre(),
                        m.getYear()
                ))
                .toList();
    }
}

