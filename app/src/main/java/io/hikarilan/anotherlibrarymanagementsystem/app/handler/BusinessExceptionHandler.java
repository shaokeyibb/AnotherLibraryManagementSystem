package io.hikarilan.anotherlibrarymanagementsystem.app.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.Error;
import io.hikarilan.anotherlibrarymanagementsystem.app.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Error> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<Error> handleNotLoginException(NotLoginException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(NotRoleException.class)
    public ResponseEntity<Error> handleNotRoleException(NotRoleException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error(e.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Error> handleBusinessException(BusinessException e) {
        return e.handleError();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e.getMessage()));
    }

}
