package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    HashMap<String, Movie> movieDb = new HashMap<>();

    HashMap<String, Director> directorDb = new HashMap<>();

    HashMap<String, List<String>> movieDirectorDb = new HashMap<>();

    public void addMovie(Movie movie) {
        String key = movie.getName();
        movieDb.put(key, movie);
    }

    public void addDirector(Director director) {
        String key = director.getName();
        directorDb.put(key, director);
    }

    public void addMovieDirectorPair(String movie, String director) {

        if(movieDb.containsKey(movie) && directorDb.containsKey(director)){
            List<String> currentMoviesByDirector = new ArrayList<>();

            if(movieDirectorDb.containsKey(director)) {
                currentMoviesByDirector = movieDirectorDb.get(director);
            }
                currentMoviesByDirector.add(movie);

                movieDirectorDb.put(movie, currentMoviesByDirector);
        }

    }

    public Movie getMovieByName(String name) {
        return movieDb.get(name);
    }

    public Director getDirectorByName(String name) {
        return directorDb.get(name);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        List<String> movies = new ArrayList<>();
            if(movieDirectorDb.containsKey(directorName)){
                movies = movieDirectorDb.get(directorName);
            }
        return movies;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movieDb.keySet());
//        List<String> listOfMovies = new ArrayList<>();
//        for (String movieNames : movieDb.keySet()){
//            listOfMovies.add(movieNames);
//        }
//        return listOfMovies;
    }

    public void deleteDirectorByName(String director) {
        List<String> movies = new ArrayList<String>();

        if (movieDirectorDb.containsKey(director)) {
            //1. Find the movie names by director from the pair
            movies = movieDirectorDb.get(director);

            //2. Deleting all the movies from movieDb by using movieName
            for (String movie : movies) {
                if (movieDb.containsKey(movie)) {
                    movieDb.remove(movie);
                }
            }

            //3. Deleteing the pair
            movieDirectorDb.remove(director);
        }

        //4. Delete the director from directorDb.
        if(directorDb.containsKey(director)){
            directorDb.remove(director);
        }
    }

    public void deleteAllDirectors() {
        HashSet<String> moviesSet = new HashSet<String>();

        //Deleting the director's map
        directorDb = new HashMap<>();

        //Finding out all the movies by all the directors combined
        for(String director: movieDirectorDb.keySet()){

            //Iterating in the list of movies by a director.
            for(String movie: movieDirectorDb.get(director)){
                moviesSet.add(movie);
            }
        }

        //Deleting the movie from the movieDb.
        for(String movie: moviesSet){
            if(movieDb.containsKey(movie)){
                movieDb.remove(movie);
            }
        }
        //clearing the pair.
        movieDirectorDb = new HashMap<>();
    }
}
