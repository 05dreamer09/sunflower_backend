package Sunflower.Sunflowerspring;

import Sunflower.Sunflowerspring.repository.Favorite_Genres_MemoryRepository;
import Sunflower.Sunflowerspring.repository.Favorite_Genres_Repository;
import Sunflower.Sunflowerspring.service.Favorite_Genres_Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
public class SunflowerConfig {
    private DataSource dataSource;

    @Bean
    public Favorite_Genres_Repository favorite_genres_repository()  {
        return new Favorite_Genres_MemoryRepository();
    }
    @Bean
    public Favorite_Genres_Service favorite_genres_service() { return new Favorite_Genres_Service(favorite_genres_repository());}


}
