package Sunflower.Sunflowerspring.repository;

import Sunflower.Sunflowerspring.domain.Favorite_Movie;

import java.util.List;
import java.util.Optional;

public interface Favorite_Movie_Repository {
    Favorite_Movie save(Favorite_Movie favorite_movie);
    Optional<Favorite_Movie> findById(Long id);
    Optional<Favorite_Movie> findByMovie1(String movie1);
    Optional<Favorite_Movie> findByMovie2(String movie2);
    List<Favorite_Movie> findAll();
}
