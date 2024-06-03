package Sunflower.Sunflowerspring.service;


import Sunflower.Sunflowerspring.domain.Favorite_Genres;
import Sunflower.Sunflowerspring.domain.Favorite_Movie;
import Sunflower.Sunflowerspring.dto.Favorite_Genres_Dto;
import Sunflower.Sunflowerspring.dto.Favorite_Movie_Dto;
import Sunflower.Sunflowerspring.dto.ReturnMovies_Dto;
import Sunflower.Sunflowerspring.repository.Favorite_Genres_Repository;
import Sunflower.Sunflowerspring.repository.Favorite_Movie_Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
            String scriptPath = "movie2.py";
            // String scriptPath = "/home/ubuntu/movie.py";

            String a1 = "\'"+favorite_movie_dto.getMovie1()+"\'";
            String a2 = "\'"+favorite_movie_dto.getMovie2()+"\'";
            String a3 = "\'"+favorite_movie_dto.getMovie3()+"\'";


            // 파이썬 실행 명령 준비
            ProcessBuilder pb = new ProcessBuilder(pythonPath, scriptPath, a1, a2, a3);


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
            ReturnMovies_Dto returnMovies = new ReturnMovies_Dto(movies[0], movies[1], movies[2], movies[3], movies[4], movies[5], movies[6], movies[7], movies[8], movies[9]);
            // 결과 반환
            return returnMovies;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }


    //그거뭐냐 포스터 리턴용
    private final String API_KEY = "a66d8479b7dc638635b13d32680073a1";
    private final String TMDB_SEARCH_URL = "https://api.themoviedb.org/3/search/movie?api_key={apiKey}&query={query}&language=ko-KR";

    //@Autowired
    private RestTemplate restTemplate = new RestTemplate();

    public String getMoviePosterUri(String movieTitle) {
        // TMDB API 호출을 위한 URL 및 파라미터 설정
        String url = TMDB_SEARCH_URL.replace("{apiKey}", API_KEY).replace("{query}", movieTitle);

        // TMDB API 호출 및 응답 수신
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String responseBody = responseEntity.getBody();

        // 응답 결과에서 포스터 URI 추출
        String posterUri = extractPosterUri(responseBody);

        return posterUri;
    }

    private String extractPosterUri(String responseBody) {
        // 여기서는 간단하게 JSON 파싱을 수행하고 첫 번째 결과의 포스터 URI를 반환하는 것으로 가정합니다.
        // 실제로는 JSON 파싱을 더 강력하게 수행해야 합니다.
        // Gson, Jackson 등의 라이브러리를 사용하면 편리합니다.

        // 간단한 JSON 파싱 예시
        // 이 부분은 실제 TMDB API 응답에 따라서 변경될 수 있습니다.
        // 예시로 간단하게 구현했으니 실제로는 더 복잡한 JSON 파싱이 필요할 수 있습니다.
        // 실제 구현에 따라 JSON 파싱 라이브러리 (Gson, Jackson 등)을 사용하는 것이 좋습니다.
        int startIndex = responseBody.indexOf("\"poster_path\":");
        if (startIndex == -1) {
            return "No poster available";
        }
        startIndex += "\"poster_path\":".length();
        int endIndex = responseBody.indexOf(",", startIndex);
        String posterPath = responseBody.substring(startIndex, endIndex);
        posterPath = posterPath.replaceAll("\"", ""); // 따옴표 제거

        // 포스터 URI 생성
        String posterUri = "https://image.tmdb.org/t/p/original" + posterPath;

        return posterUri;
    }


}


