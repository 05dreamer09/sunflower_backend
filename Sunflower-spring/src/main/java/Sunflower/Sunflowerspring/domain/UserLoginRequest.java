package Sunflower.Sunflowerspring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserLoginRequest {
    private String userName;
    private String password;

}
