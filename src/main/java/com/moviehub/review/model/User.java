package com.moviehub.review.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
public class User {
    private String userId;
    private List<String> movieIds;
    private List<String> reviewIds;
    private String userName;
    private String emailId;
}
