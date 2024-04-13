package Sunflower.Sunflowerspring.service;


import Sunflower.Sunflowerspring.domain.Favorite_Genres;
import Sunflower.Sunflowerspring.domain.Favorite_Movie;
import Sunflower.Sunflowerspring.dto.Favorite_Genres_Dto;
import Sunflower.Sunflowerspring.dto.Favorite_Movie_Dto;
import Sunflower.Sunflowerspring.repository.Favorite_Genres_Repository;
import Sunflower.Sunflowerspring.repository.Favorite_Movie_Repository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class Favorite_Movie_Service {


    //******************************************************************************************
    private final Favorite_Movie_Repository favorite_movie_repository;


    public Favorite_Movie_Service(Favorite_Movie_Repository favorite_movie_repository) {
        this.favorite_movie_repository = favorite_movie_repository;
    }

    public void saveFavoriteMovie(Favorite_Movie_Dto favorite_movie_dto) {
        Favorite_Movie favorite_movie = new Favorite_Movie();

        favorite_movie.setMovie1(favorite_movie_dto.getMovie1());
        favorite_movie.setMovie2(favorite_movie_dto.getMovie2());

        favorite_movie_repository.save(favorite_movie);
    }

    public Favorite_Movie_Dto findMovieByMovie1(String movie1)    {
        Favorite_Movie favorite_movie = favorite_movie_repository.findByMovie1(movie1).get();

        Favorite_Movie_Dto favorite_movie_dto = new Favorite_Movie_Dto();
        favorite_movie_dto.setMovie1(favorite_movie.getMovie1());
        favorite_movie_dto.setMovie2(favorite_movie.getMovie2());
        favorite_movie_dto.setMovie3(favorite_movie.getMovie3());

        return favorite_movie_dto;
    }
    //*************************************************************************************************
    //사실상 여기 필요없음. 리포지토리도 따로 만들필요없음.

    public String returnMovie(Favorite_Movie_Dto favorite_movie_dto) {
        try {
            String pythonPath = "python3";

            // 파이썬 스크립트 경로
            String scriptPath = "../../../movie.py";
            // String scriptPath = "/home/ubuntu/movie.py";

            // 파이썬 실행 명령 준비
            ProcessBuilder pb = new ProcessBuilder(pythonPath, scriptPath, favorite_movie_dto.getMovie1(), favorite_movie_dto.getMovie2(), favorite_movie_dto.getMovie3());

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


