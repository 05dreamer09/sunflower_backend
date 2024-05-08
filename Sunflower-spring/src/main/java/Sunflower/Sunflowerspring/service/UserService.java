package Sunflower.Sunflowerspring.service;

import Sunflower.Sunflowerspring.domain.User;
import Sunflower.Sunflowerspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public String join(String userName, String password) {

        //중복 체크 기능 - userName : 이걸 하려면 db에 갔다와야함
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new RuntimeException(userName + "는 이미 존재하는 아이디입니다.");
                });

        //JPA를 통해 데이터베이스에 객체 저장
        User user = User.builder()
                .userName(userName)
                .password(encoder.encode(password))
                .build();
        userRepository.save(user);

        return "회원가입 완료";
    }

    public String login(String userName, String password) {
        //userName없음
        userRepository.findByUserName(userName)
                .orElseThrow(() ->new RuntimeException("등록된 userName이 없습니다"));
        //userName 틀림

        return "token";
    }
}
