package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    HashMap<String, Movie> movieDb = new HashMap<>();

    HashMap<String, Director> directorDb = new HashMap<>();

    HashMap<String, String> movieDirectorDb = new HashMap<>();

    public void addMovie(Movie movie) {
        String key = movie.getName();
        movieDb.put(key, movie);
    }

    public void addDirector(Director director) {
        String key = director.getName();
        directorDb.put(key, director);
    }

    public void addMovieDirectorPair(String movie, String director) {
        movieDirectorDb.put(movie, director);
    }

    public Movie getMovieByName(String name) {
        return movieDb.get(name);
    }

    public Director getDirectorByName(String name) {
        return directorDb.get(name);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        List<String> movies = new ArrayList<>();
        for (Map.Entry<String, String> entry : movieDirectorDb.entrySet()) {
            if(entry.getValue().equals(directorName)){
                String movie = entry.getKey();
                movies.add(movie);
            }
        }
        return movies;
    }

    public List<String> findAllMovies() {
        List<String> listOfMovies = new ArrayList<>();
        for (String movieNames : movieDb.keySet()){
            listOfMovies.add(movieNames);
        }
        return listOfMovies;
    }

    public void deleteDirectorByName(String director) {
        directorDb.remove(director);
        for(Map.Entry<String,String> entry : movieDirectorDb.entrySet()){
            if(entry.getValue().equals(director)){
                String movie = entry.getKey();
                movieDb.remove(movie);
                movieDirectorDb.remove(movie,director);
            }
        }

    }

    public void deleteAllDirectors() {
        for (Map.Entry<String,String> entry : movieDirectorDb.entrySet()) {
            String movie = entry.getKey();
            String director = entry.getValue();
            movieDirectorDb.remove(movie,director);
            movieDb.remove(movie);
            directorDb.remove(director);
        }
    }
}
