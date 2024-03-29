package Sunflower.Sunflowerspring.controller;

import Sunflower.Sunflowerspring.dto.Favorite_Genres_Dto;
import Sunflower.Sunflowerspring.service.Favorite_Genres_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Favorite_Genres_Controller {

    private final Favorite_Genres_Service favorite_genres_service;

    @Autowired
    public Favorite_Genres_Controller(Favorite_Genres_Service favorite_genres_service) { this.favorite_genres_service = favorite_genres_service;    }

    @PostMapping("favoritegenres")
    public String save( Favorite_Genres_Dto favorite_genres_dto)  {
        favorite_genres_service.saveFavoriteGenres(favorite_genres_dto);
        return favorite_genres_service.returnMovie(favorite_genres_dto);
    }

    @GetMapping("FavoriteGenres/{genre1}")
    public Favorite_Genres_Dto findFGByGenre1(@PathVariable String genre1)  {
        return favorite_genres_service.findFGByGenre1(genre1);
    }

    @GetMapping("asdf")
    public String asdf()
    {
        return "장현준 바보";
    }




}
