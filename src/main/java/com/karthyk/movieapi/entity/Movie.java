package com.karthyk.movieapi.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity @Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    private Integer year;

    @Column(nullable = false)
    private String genre;

}
