package Sunflower.Sunflowerspring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Users {
    @Column(name = "user_name") // 컬럼 이름 지정
    private String userName;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    private String password;
}
