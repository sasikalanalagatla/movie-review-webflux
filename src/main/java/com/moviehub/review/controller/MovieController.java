package com.moviehub.review.controller;

import com.moviehub.review.dto.MovieRequestDto;
import com.moviehub.review.dto.MovieResponseDto;
import com.moviehub.review.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieService;

    @PostMapping("/save")
    public Mono<MovieResponseDto> createMovie(@RequestBody MovieRequestDto movieRequestDto){
        return movieService.createMovie(movieRequestDto);
    }

    @GetMapping("/{movieId}")
    public Mono<MovieResponseDto> getMovieById(@PathVariable String movieId){
        return movieService.getMovieById(movieId);
    }

    @GetMapping("/all")
    public Flux<MovieResponseDto> getAllMovies(){
        return movieService.getALlMovies();
    }

    @PutMapping("/update/{movieId}")
    public Mono<MovieResponseDto> updateMovie(@PathVariable String movieId, @RequestBody MovieRequestDto movieRequestDto){
        return movieService.updateMovie(movieId,movieRequestDto);
    }

    @DeleteMapping("delete/{movieId}")
    private Mono<Void> deleteMovie(@PathVariable String movieId){
        return movieService.deleteMovie(movieId);
    }
}
