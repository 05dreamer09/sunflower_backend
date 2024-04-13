package Sunflower.Sunflowerspring.controller;

import Sunflower.Sunflowerspring.dto.Favorite_Genres_Dto;
import Sunflower.Sunflowerspring.dto.Favorite_Movie_Dto;
import Sunflower.Sunflowerspring.dto.ReturnMovies_Dto;
import Sunflower.Sunflowerspring.service.Favorite_Genres_Service;
import Sunflower.Sunflowerspring.service.Favorite_Movie_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"https://resttesttest.com/", "http://127.0.0.1/", "https://125.178.91.189/", "http://125.178.91.189", "125.178.91.189"}) // 컨트롤러에서 설정
@RestController
public class Favorite_Movie_Controller {

    private final Favorite_Movie_Service favorite_movie_service;

    @Autowired
    public Favorite_Movie_Controller(Favorite_Movie_Service favorite_movie_service) { this.favorite_movie_service = favorite_movie_service;    }

    @PostMapping("favoritemovie")
    public ReturnMovies_Dto returnMovie(Favorite_Movie_Dto favorite_movie_dto)  {

        return favorite_movie_service.returnMovie(favorite_movie_dto);

    }

}
