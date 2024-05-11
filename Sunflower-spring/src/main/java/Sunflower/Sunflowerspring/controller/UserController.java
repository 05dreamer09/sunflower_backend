package Sunflower.Sunflowerspring.controller;


import Sunflower.Sunflowerspring.domain.UserJoinRequest;
import Sunflower.Sunflowerspring.domain.UserLoginRequest;
import Sunflower.Sunflowerspring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"https://resttesttest.com/", "https://movie-recommendation.kro.kr", "203.253.23.10"}) // 컨트롤러에서 설정
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    @PostMapping("/api/join")
    public ResponseEntity<String> join(UserJoinRequest UserJoinRequest) {
        String name = UserJoinRequest.getUserName();
        userService.join(UserJoinRequest);
        return ResponseEntity.ok().body(name + "의 회원가입이 성공했습니다.");
    }

    @PostMapping("/api/login") //로그인 요청하면 token을 발급함
    public ResponseEntity<String> login(UserLoginRequest userLoginRequest) {
        log.info(userLoginRequest.getId(), userLoginRequest.getPassword());
        String token = userService.login(userLoginRequest); //로그인에 성공하면 token리턴함
        return ResponseEntity.ok().body("success " + token); //성공하면 body에 token 실어서 넘김
    }
}
