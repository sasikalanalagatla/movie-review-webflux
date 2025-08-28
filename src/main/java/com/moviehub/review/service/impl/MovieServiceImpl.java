package com.moviehub.review.service.impl;

import com.moviehub.review.dto.MovieRequestDto;
import com.moviehub.review.dto.MovieResponseDto;
import com.moviehub.review.exception.MovieNotFoundException;
import com.moviehub.review.mapper.MovieMapper;
import com.moviehub.review.model.Movie;
import com.moviehub.review.repository.MovieRepository;
import com.moviehub.review.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Mono<MovieResponseDto> createMovie(MovieRequestDto movieRequestDto) {
        Movie movie = MovieMapper.toEntity(movieRequestDto);
        return movieRepository.save(movie)
                .map(MovieMapper::toDto);
    }

    @Override
    public Mono<MovieResponseDto> getMovieById(String movieId) {
        return movieRepository.findById(movieId)
                .switchIfEmpty(Mono.error(new MovieNotFoundException("Movie not found with movieId: " + movieId)))
                .map(MovieMapper::toDto);
    }

    @Override
    public Flux<MovieResponseDto> getALlMovies() {
        return movieRepository.findAll().map(MovieMapper::toDto);
    }

    @Override
    public Mono<MovieResponseDto> updateMovie(String movieId, MovieRequestDto movieRequestDto) {
        return movieRepository.findById(movieId)
                .switchIfEmpty(Mono.error(new MovieNotFoundException("Movie not found with movieId: " + movieId)))
                .flatMap(existingMovie -> {
                    existingMovie.setTitle(movieRequestDto.getTitle());
                    existingMovie.setGenres(movieRequestDto.getGenre());
                    existingMovie.setReleasedYear(movieRequestDto.getReleaseYear());
                    return movieRepository.save(existingMovie);
                })
                .map(MovieMapper::toDto);
    }

    @Override
    public Mono<Void> deleteMovie(String movieId) {
        return movieRepository.deleteById(movieId);
    }
}
