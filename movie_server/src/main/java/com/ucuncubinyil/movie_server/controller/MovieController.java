package com.ucuncubinyil.movie_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucuncubinyil.movie_server.domain.Movie;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @GetMapping("/list")
    public List<Movie> getAllMovies() {

        List<Movie> movies = new ArrayList<Movie>();

        movies.add(new Movie("Kelebek Etkisi", "2004", "7,7"));
        movies.add(new Movie("Zamanın Ötesinde", "2014", "7,5"));

        return movies;
    }
}