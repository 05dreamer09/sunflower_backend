package Sunflower.Sunflowerspring.service;

import Sunflower.Sunflowerspring.dto.Favorite_Genres_Dto;
import Sunflower.Sunflowerspring.repository.Favorite_Genres_MemoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Favorite_Genres_Service_Test {

    Favorite_Genres_Service favorite_genres_service;
    Favorite_Genres_MemoryRepository favorite_genres_memoryRepository;

    @BeforeEach
    public void beforeEach()    {
        favorite_genres_memoryRepository = new Favorite_Genres_MemoryRepository();
        favorite_genres_service = new Favorite_Genres_Service(favorite_genres_memoryRepository);
    }

    @AfterEach
    public void afterEach() { favorite_genres_memoryRepository.clearStore();}

    @Test
    public void saveAndFindGenreTest()  {
        Favorite_Genres_Dto favorite_genres_dto = new Favorite_Genres_Dto();
        favorite_genres_dto.setGenre1("공포");
        favorite_genres_dto.setGenre2("액션");
        favorite_genres_dto.setGenre3("장현준");

        favorite_genres_service.saveFavoriteGenres(favorite_genres_dto);

        Favorite_Genres_Dto testGenre1 = favorite_genres_service.findFGByGenre1("공포");

        Assertions.assertThat(favorite_genres_dto.getGenre1()).isEqualTo(testGenre1.getGenre1());
    }





}
