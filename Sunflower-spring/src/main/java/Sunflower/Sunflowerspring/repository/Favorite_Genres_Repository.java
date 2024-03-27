package Sunflower.Sunflowerspring.repository;

import Sunflower.Sunflowerspring.domain.Favorite_Genres;

import java.util.List;
import java.util.Optional;

public interface Favorite_Genres_Repository {
    Favorite_Genres save(Favorite_Genres favorite_genres);
    Optional<Favorite_Genres> findById(Long id);
    Optional<Favorite_Genres> findByGenre1(String genre1);
    Optional<Favorite_Genres> findByGenre2(String genre2);
    List<Favorite_Genres> findAll();
}
