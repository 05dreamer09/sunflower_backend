package Sunflower.Sunflowerspring.repository;

import Sunflower.Sunflowerspring.domain.Favorite_Genres;
import Sunflower.Sunflowerspring.domain.Favorite_Movie;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class Favorite_Movie_MemoryRepository implements Favorite_Movie_Repository{

    private static Map<Long, Favorite_Movie> store = new HashMap<>(); // 저장 공간
    private static Long sequence = 0L; // id를 생성하기 위한 sequence

    @Override
    public Optional<Favorite_Movie> findByMovie1(String movie1) {
        return store.values().stream()
                .filter(favorite_movie -> favorite_movie.getMovie1().equals(movie1))
                .findAny();
    }

    @Override
    public Optional<Favorite_Movie> findByMovie2(String movie2) {
        return store.values().stream()
                .filter(favorite_movie -> favorite_movie.getMovie2().equals(movie2))
                .findAny();
    }

    @Override
    public Optional<Favorite_Movie> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }


    @Override
    public Favorite_Movie save(Favorite_Movie favorite_movie) {
        favorite_movie.setMid(++sequence);
        store.put(favorite_movie.getMid(), favorite_movie);
        return favorite_movie;
    }

    @Override
    public List<Favorite_Movie> findAll() {return store.values().stream().toList();}

    public void clearStore()    {
        store.clear();
    }
}
