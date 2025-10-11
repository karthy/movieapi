package com.karthyk.movieapi.repository;

import com.karthyk.movieapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> { }

