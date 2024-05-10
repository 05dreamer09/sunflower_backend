package Sunflower.Sunflowerspring.service;

import Sunflower.Sunflowerspring.domain.User;
import Sunflower.Sunflowerspring.domain.UserJoinRequest;
import Sunflower.Sunflowerspring.exception.AppException;
import Sunflower.Sunflowerspring.exception.ErrorCode;
import Sunflower.Sunflowerspring.repository.UserRepository;
import Sunflower.Sunflowerspring.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String key;

    private Long expireTimeMs = 1000 * 60 * 60L;

    public String join(UserJoinRequest dto) {

        //중복 체크 기능 - userName : 이걸 하려면 db에 갔다와야함
        userRepository.findByUserName(dto.getUserName())
                .ifPresent(user -> {
                    throw new RuntimeException(dto.getUserName() + "는 이미 존재하는 아이디입니다.");
                });

        //JPA를 통해 데이터베이스에 객체 저장
        User user = User.builder()
                .userName(dto.getUserName())
                .password(encoder.encode(dto.getPassword()))
                .build();
        userRepository.save(user);

        return "회원가입 완료";
    }

    public String login(String userName, String password) {
        //userName없음
        User selectedUser = userRepository. findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, userName + "이 없습니다"));

        //password 틀림
        if (!encoder.matches(password, selectedUser.getPassword())) {
            //매치가 안된다면
            throw new AppException(ErrorCode.INVALID_PASSWORD, "비밀번호를 잘못 입력했습니다.");
        }

        //앞의 두 과정에서 예외에러가 없었으면 token발행함
        String token = JwtTokenUtil.createToken(selectedUser.getUserName(), key, expireTimeMs);
        return token;
    }
}
