package com.moviehub.review.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieRequestDto {
    private String title;
    private List<String> genre;
    private Integer releaseYear;
}