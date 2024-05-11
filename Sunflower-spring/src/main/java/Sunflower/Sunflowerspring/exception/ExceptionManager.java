package Sunflower.Sunflowerspring.exception;

import Sunflower.Sunflowerspring.dto.JoinReturnDto;
import Sunflower.Sunflowerspring.dto.LoginReturnDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
    //예외 처리를 하는 클래스

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleAppException(AppException e) {
        if (e.getErrorCode() == ErrorCode.ID_ERROR) {
            return joinExceptionHandler(e);
        } else {
            return loginExceptionHandler(e);
        }
    }

    public ResponseEntity<JoinReturnDto> joinExceptionHandler(AppException e) {
        JoinReturnDto returnDto = new JoinReturnDto();
        returnDto.setStatus(400);
        returnDto.setMessage("ID_ERROR");
        return ResponseEntity.ok().body(returnDto);
    }

    public ResponseEntity<LoginReturnDto> loginExceptionHandler(AppException e) {
        ErrorCode error = e.getErrorCode();
        LoginReturnDto loginReturnDto = new LoginReturnDto();
        loginReturnDto.setStatus(400);
        if (error == ErrorCode.ID_ERROR) {
            loginReturnDto.setMessage("ID_ERROR");
        } else {
            loginReturnDto.setMessage("PASSWORD_ERROR");
        }
        return ResponseEntity.ok().body(loginReturnDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        //ResponseEntity<?>은 바디에 어떤 것이든 올 수 있다는 것이다.
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
}
