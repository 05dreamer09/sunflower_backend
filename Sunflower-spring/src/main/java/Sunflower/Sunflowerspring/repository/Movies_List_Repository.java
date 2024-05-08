package Sunflower.Sunflowerspring.repository;

import Sunflower.Sunflowerspring.domain.Favorite_Movie;
import Sunflower.Sunflowerspring.dto.Favorite_Genres_Dto;
import Sunflower.Sunflowerspring.dto.GenreMovies;

import java.util.List;
import java.util.Optional;

public interface Movies_List_Repository {

    GenreMovies findByGenres(Favorite_Genres_Dto favorite_genres_dto);
}
