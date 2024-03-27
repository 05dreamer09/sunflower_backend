package Sunflower.Sunflowerspring.repository;

import Sunflower.Sunflowerspring.domain.Favorite_Genres;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class Favorite_Genres_MemoryRepository implements Favorite_Genres_Repository{

    private static Map<Long, Favorite_Genres> store = new HashMap<>(); // 저장 공간
    private static Long sequence = 0L; // id를 생성하기 위한 sequence


    @Override
    public Favorite_Genres save(Favorite_Genres favorite_genres) {
        favorite_genres.setgid(++sequence);
        store.put(favorite_genres.getgid(), favorite_genres);
        return favorite_genres;
    }

    @Override
    public Optional<Favorite_Genres> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Favorite_Genres> findByGenre1(String genre1) {
        return store.values().stream()
                .filter(favorite_genres -> favorite_genres.getGenre1().equals(genre1))
                .findAny();
    }
    @Override
    public Optional<Favorite_Genres> findByGenre2(String genre2) {
        return store.values().stream()
                .filter(favorite_genres -> favorite_genres.getGenre2().equals(genre2))
                .findAny();
    }

    @Override
    public List<Favorite_Genres> findAll() {return store.values().stream().toList();}

    public void clearStore()    {
        store.clear();
    }
}
