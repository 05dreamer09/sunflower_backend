package Sunflower.Sunflowerspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @PostMapping("/api/reviews")
    public ResponseEntity<String> writeReview() {
        return ResponseEntity.ok().body("리뷰 등록 완료");
    }
}
