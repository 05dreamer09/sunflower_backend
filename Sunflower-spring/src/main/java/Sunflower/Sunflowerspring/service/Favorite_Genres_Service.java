package Sunflower.Sunflowerspring.service;


import Sunflower.Sunflowerspring.domain.Favorite_Genres;
import Sunflower.Sunflowerspring.dto.Favorite_Genres_Dto;
import Sunflower.Sunflowerspring.repository.Favorite_Genres_Repository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class Favorite_Genres_Service {

    private final Favorite_Genres_Repository favorite_genres_repository;

    public Favorite_Genres_Service(Favorite_Genres_Repository favorite_genres_repository) {
        this.favorite_genres_repository = favorite_genres_repository;
    }

    public void saveFavoriteGenres(Favorite_Genres_Dto favorite_genres_dto) {
        Favorite_Genres favorite_genres = new Favorite_Genres();

        favorite_genres.setGenre1(favorite_genres_dto.getGenre1());
        favorite_genres.setGenre2(favorite_genres_dto.getGenre2());
        favorite_genres.setGenre3(favorite_genres_dto.getGenre3());

        favorite_genres_repository.save(favorite_genres);
    }

    public Favorite_Genres_Dto findFGByGenre1(String genre1)    {
        Favorite_Genres favorite_genres = favorite_genres_repository.findByGenre1(genre1).get();

        Favorite_Genres_Dto favorite_genres_dto = new Favorite_Genres_Dto();
        favorite_genres_dto.setGenre1(favorite_genres.getGenre1());
        favorite_genres_dto.setGenre2(favorite_genres.getGenre2());
        favorite_genres_dto.setGenre3(favorite_genres.getGenre3());

        return favorite_genres_dto;
    }

    public Favorite_Genres_Dto findFGByGenre2(String genre2)    {
        Favorite_Genres favorite_genres = favorite_genres_repository.findByGenre1(genre2).get();

        Favorite_Genres_Dto favorite_genres_dto = new Favorite_Genres_Dto();
        favorite_genres_dto.setGenre1(favorite_genres.getGenre1());
        favorite_genres_dto.setGenre2(favorite_genres.getGenre2());
        favorite_genres_dto.setGenre3(favorite_genres.getGenre3());

        return favorite_genres_dto;
    }

    public Favorite_Genres_Dto findFGByGenre3(String genre3)    {
        Favorite_Genres favorite_genres = favorite_genres_repository.findByGenre1(genre3).get();

        Favorite_Genres_Dto favorite_genres_dto = new Favorite_Genres_Dto();
        favorite_genres_dto.setGenre1(favorite_genres.getGenre1());
        favorite_genres_dto.setGenre2(favorite_genres.getGenre2());
        favorite_genres_dto.setGenre3(favorite_genres.getGenre3());

        return favorite_genres_dto;
    }

}


