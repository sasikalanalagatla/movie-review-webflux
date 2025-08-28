package com.moviehub.review.repository;

import com.moviehub.review.model.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends ReactiveMongoRepository<Review, String> {
}
