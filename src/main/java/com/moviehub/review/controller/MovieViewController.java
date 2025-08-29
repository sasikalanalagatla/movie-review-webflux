package com.moviehub.review.controller;

import com.moviehub.review.dto.MovieRequestDto;
import com.moviehub.review.dto.MovieResponseDto;
import com.moviehub.review.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/movie")
public class MovieViewController {

    @Autowired
    private MovieServiceImpl movieService;

    @GetMapping("/")
    public Mono<String> home() {
        return Mono.just("home");
    }

    @GetMapping("/all")
    public Mono<String> getAllMovies(Model model) {
        return movieService.getALlMovies()
                .collectList()
                .doOnNext(movies -> model.addAttribute("movies", movies))
                .thenReturn("movie-list");
    }

    @GetMapping("/add")
    public Mono<String> showAddForm(Model model) {
        model.addAttribute("movie", new MovieRequestDto());
        return Mono.just("movie-form"); // Uses movie-form.html for adding
    }

    @GetMapping("/edit/{movieId}")
    public Mono<String> showEditForm(@PathVariable String movieId, Model model) {
        return movieService.getMovieById(movieId)
                .doOnNext(movie -> model.addAttribute("movie", movie))
                .thenReturn("movie-edit-form"); // Uses movie-edit-form.html for editing
    }

    @GetMapping("/{movieId}")
    public Mono<String> getMovieById(@PathVariable String movieId, Model model) {
        return movieService.getMovieById(movieId)
                .doOnNext(movie -> model.addAttribute("movie", movie))
                .thenReturn("movie-detail");
    }

    @PostMapping("/save")
    public Mono<String> createMovie(@ModelAttribute MovieRequestDto movieRequestDto) {
        return movieService.createMovie(movieRequestDto)
                .then(Mono.just("redirect:/movie/all"));
    }

    @PostMapping("/update/{movieId}")
    public Mono<String> updateMovie(@PathVariable String movieId,
                                    @ModelAttribute MovieRequestDto movieRequestDto) {
        return movieService.updateMovie(movieId, movieRequestDto)
                .then(Mono.just("redirect:/movie/all"));
    }

    @PostMapping("/delete/{movieId}")
    public Mono<String> deleteMovie(@PathVariable String movieId) {
        return movieService.deleteMovie(movieId)
                .then(Mono.just("redirect:/movie/all"));
    }
}