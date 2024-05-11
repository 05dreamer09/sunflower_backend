package Sunflower.Sunflowerspring.exception;

import Sunflower.Sunflowerspring.dto.ReturnDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
    //예외 처리를 하는 클래스
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ReturnDto> appExceptionHandler(AppException e) {
        ReturnDto returnDto = new ReturnDto();
        returnDto.setStatus(400);
        returnDto.setMessage("ID_ERROR");
        return ResponseEntity.ok().body(returnDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        //ResponseEntity<?>은 바디에 어떤 것이든 올 수 있다는 것이다.
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
}
