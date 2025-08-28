package com.moviehub.review.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieResponseDto {
    private String id;
    private String title;
    private List<String> genre;
    private Integer releaseYear;
    private Double averageRating;
}