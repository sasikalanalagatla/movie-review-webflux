package com.moviehub.review.dto;

import lombok.Data;

@Data
public class ReviewRequestDto {
    private String movieId;
    private String userId;
    private Integer rating;
    private String comment;
}