package Sunflower.Sunflowerspring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USERNAME_DUPLICATED(HttpStatus.CONFLICT),

    ID_ERROR(HttpStatus.NOT_FOUND),

    PASSWORD_ERROR(HttpStatus.UNAUTHORIZED);

    private HttpStatus httpStatus;

}
