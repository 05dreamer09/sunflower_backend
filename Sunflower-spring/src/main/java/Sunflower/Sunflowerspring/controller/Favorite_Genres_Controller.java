package Sunflower.Sunflowerspring.controller;

import Sunflower.Sunflowerspring.dto.Favorite_Genres_Dto;
import Sunflower.Sunflowerspring.dto.GenreMovies;
import Sunflower.Sunflowerspring.service.Favorite_Genres_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"https://resttesttest.com/", "http://127.0.0.1/", "https://125.178.91.189/", "http://125.178.91.189", "125.178.91.189"}) // 컨트롤러에서 설정
@RestController
public class Favorite_Genres_Controller {

    private final Favorite_Genres_Service favorite_genres_service;

    @Autowired
    public Favorite_Genres_Controller(Favorite_Genres_Service favorite_genres_service) { this.favorite_genres_service = favorite_genres_service;    }

    @PostMapping("favoritegenres")
    public GenreMovies save( Favorite_Genres_Dto favorite_genres_dto)  {
        favorite_genres_service.saveFavoriteGenres(favorite_genres_dto);

        //예시
        GenreMovies a = new GenreMovies();
        String[] s = new String[]{"장현준1", "장현준2", "장현준3", "장현준4", "장현준5"};
        a.setMovies1(s);
        a.setMovies2(s);
        a.setMovies3(s);
        //여기에 DB에서 영화들 넘겨주는 기능 넣으면 됨.
        return a;
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
