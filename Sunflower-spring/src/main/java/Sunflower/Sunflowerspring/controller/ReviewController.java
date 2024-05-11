package Sunflower.Sunflowerspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"https://resttesttest.com/", "http://127.0.0.1/", "localhost", "localhost:80", "http://localhost", "https://localhost", "localhost:443", "https://movie-recommendation.kro.kr"}) // 컨트롤러에서 설정
@RestController
@RequiredArgsConstructor

public class ReviewController {
    @PostMapping("/api/reviews")
    public ResponseEntity<String> writeReview(Authentication authentication) {
        return ResponseEntity.ok().body(authentication.getName() + "님의 리뷰 등록 완료");
    }
}
