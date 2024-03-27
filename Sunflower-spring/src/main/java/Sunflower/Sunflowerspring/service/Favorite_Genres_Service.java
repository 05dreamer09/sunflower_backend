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

        favorite_genres_repository.save(favorite_genres);
    }

    public Favorite_Genres_Dto findFGByGenre1(String genre1)    {
        Favorite_Genres favorite_genres = favorite_genres_repository.findByGenre1(genre1).get();

        Favorite_Genres_Dto favorite_genres_dto = new Favorite_Genres_Dto();
        favorite_genres_dto.setGenre1(favorite_genres.getGenre1());
        favorite_genres_dto.setGenre2(favorite_genres.getGenre2());

        return favorite_genres_dto;
    }

    public Favorite_Genres_Dto findFGByGenre2(String genre2)    {
        Favorite_Genres favorite_genres = favorite_genres_repository.findByGenre1(genre2).get();

        Favorite_Genres_Dto favorite_genres_dto = new Favorite_Genres_Dto();
        favorite_genres_dto.setGenre1(favorite_genres.getGenre1());
        favorite_genres_dto.setGenre2(favorite_genres.getGenre2());

        return favorite_genres_dto;
    }


    public String returnMovie(Favorite_Genres_Dto favorite_genres_dto) {
        try {
            String pythonPath = "python";

            // 파이썬 스크립트 경로
            String scriptPath = "C:\\Users\\rlawo\\OneDrive\\바탕 화면\\movie.py";

            // 파이썬 실행 명령 준비
            ProcessBuilder pb = new ProcessBuilder(pythonPath, scriptPath, favorite_genres_dto.getGenre1(), favorite_genres_dto.getGenre2());

            // 프로세스 실행
            Process process = pb.start();

            // 스트림을 통해 파이썬 출력 받기
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // 한 줄만 읽어옴
            String result = reader.readLine();

            // 프로세스가 완료될 때까지 대기
            process.waitFor();

            // 결과 반환
            return result;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

}


