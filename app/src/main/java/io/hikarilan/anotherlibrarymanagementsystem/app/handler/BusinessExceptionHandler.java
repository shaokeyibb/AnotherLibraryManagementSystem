package io.hikarilan.anotherlibrarymanagementsystem.app.handler;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.Error;
import io.hikarilan.anotherlibrarymanagementsystem.app.exception.InvalidUsernameOrPasswordException;
import io.hikarilan.anotherlibrarymanagementsystem.app.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    public ResponseEntity<Error> handleInvalidUsernameOrPasswordException(InvalidUsernameOrPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Error> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Error> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e.getMessage()));
    }

}
