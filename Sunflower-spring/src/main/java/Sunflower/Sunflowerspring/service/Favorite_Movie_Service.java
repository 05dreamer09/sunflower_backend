package Sunflower.Sunflowerspring.service;


import Sunflower.Sunflowerspring.domain.Favorite_Genres;
import Sunflower.Sunflowerspring.domain.Favorite_Movie;
import Sunflower.Sunflowerspring.dto.Favorite_Genres_Dto;
import Sunflower.Sunflowerspring.dto.Favorite_Movie_Dto;
import Sunflower.Sunflowerspring.dto.ReturnMovies_Dto;
import Sunflower.Sunflowerspring.repository.Favorite_Genres_Repository;
import Sunflower.Sunflowerspring.repository.Favorite_Movie_Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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

    public ReturnMovies_Dto returnMovie(Favorite_Movie_Dto favorite_movie_dto) {
        try {
            String pythonPath = "python3";

            // 파이썬 스크립트 경로
            String scriptPath = "../../../movie2.py";
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

            String[] movies = result.split(", ");
            ReturnMovies_Dto returnMovies = new ReturnMovies_Dto(movies[0], movies[1], movies[2], movies[3], movies[4]);
            // 결과 반환
            return returnMovies;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }


    //그거뭐냐 포스터 리턴용
    private final String apiKey = "a66d8479b7dc638635b13d32680073a1";

    private final RestTemplate restTemplate = new RestTemplate();


    public String getMoviePosterUrl(String movieTitle) {
        try {
            String encodedTitle = URLEncoder.encode(movieTitle, StandardCharsets.UTF_8);
            String apiUrl = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + encodedTitle;

            ResponseEntity<TMDBResponse> response = restTemplate.getForEntity(apiUrl, Favorite_Movie_Service.TMDBResponse.class);

            if (response.getBody() != null && response.getBody().getResults() != null && response.getBody().getResults().length > 0) {
                String posterPath = response.getBody().getResults()[0].getPosterPath();
                return "https://image.tmdb.org/t/p/original" + posterPath;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static class TMDBResponse {
        private Favorite_Movie_Service.TMDBMovie[] results;

        public Favorite_Movie_Service.TMDBMovie[] getResults() {
            return results;
        }

        public void setResults(Favorite_Movie_Service.TMDBMovie[] results) {
            this.results = results;
        }
    }

    static class TMDBMovie {
        private String poster_path;

        public String getPosterPath() {
            return poster_path;
        }

        public void setPosterPath(String poster_path) {
            this.poster_path = poster_path;
        }
    }

}


