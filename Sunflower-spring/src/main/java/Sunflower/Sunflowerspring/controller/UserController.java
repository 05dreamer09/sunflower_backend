package Sunflower.Sunflowerspring.controller;


import Sunflower.Sunflowerspring.domain.UserJoinRequest;
import Sunflower.Sunflowerspring.domain.UserLoginRequest;
import Sunflower.Sunflowerspring.dto.JoinReturnDto;
import Sunflower.Sunflowerspring.dto.LoginReturnDto;
import Sunflower.Sunflowerspring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"https://resttesttest.com/", "https://movie-recommendation.kro.kr", "203.253.23.10" , "http://localhost:2020/"}) // 컨트롤러에서 설정
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/api/join")
    public ResponseEntity<JoinReturnDto> join(UserJoinRequest UserJoinRequest) {
        String name = UserJoinRequest.getUserName();
        userService.join(UserJoinRequest);

        JoinReturnDto ss = new JoinReturnDto();
        ss.setStatus(200);
        ss.setMessage("회원가입 성공");

        return ResponseEntity.ok().body(ss);
    }

    @PostMapping("/api/login") //로그인 요청하면 token을 발급함
    public ResponseEntity<LoginReturnDto> login(UserLoginRequest userLoginRequest) {
        LoginReturnDto loginReturnDto = new LoginReturnDto();
        String token = userService.login(userLoginRequest); //로그인에 성공하면 token리턴함

        loginReturnDto.setStatus(200);
        loginReturnDto.setMessage("로그인 성공");
        loginReturnDto.setAccessToken(token);
        return ResponseEntity.ok().body(loginReturnDto); //성공하면 body에 token 실어서 넘김
    }
}
