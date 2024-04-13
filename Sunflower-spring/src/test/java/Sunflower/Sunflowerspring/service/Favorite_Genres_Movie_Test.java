package Sunflower.Sunflowerspring.service;

import Sunflower.Sunflowerspring.dto.Favorite_Genres_Dto;
import Sunflower.Sunflowerspring.dto.Favorite_Movie_Dto;
import Sunflower.Sunflowerspring.dto.ReturnMovies_Dto;
import Sunflower.Sunflowerspring.repository.Favorite_Genres_MemoryRepository;
import Sunflower.Sunflowerspring.repository.Favorite_Movie_MemoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Favorite_Genres_Movie_Test {

    Favorite_Movie_Service favorite_movie_service;
    Favorite_Movie_MemoryRepository favorite_movie_memoryRepository;

    @BeforeEach
    public void beforeEach()    {
        favorite_movie_memoryRepository = new Favorite_Movie_MemoryRepository();
        favorite_movie_service = new Favorite_Movie_Service(favorite_movie_memoryRepository);
    }

    @AfterEach
    public void afterEach() { favorite_movie_memoryRepository.clearStore();}

/*
    @Test
    public void returnMovieTest()   {
        Favorite_Movie_Dto favorite_movie_dto = new Favorite_Movie_Dto();
        favorite_movie_dto.setMovie1("범죄도시1");
        favorite_movie_dto.setMovie2("범죄도시2");
        favorite_movie_dto.setMovie3("범죄도시3");

        ReturnMovies_Dto movies;
        movies = favorite_movie_service.returnMovie(favorite_movie_dto);
*/
        /*
        assertEquals("범죄도시4", result);

    }
*/



}
