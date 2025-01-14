package io.hikarilan.anotherlibrarymanagementsystem.app.exception;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.BizConstants;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.Error;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserAlreadyExistsException extends BusinessException {

    public UserAlreadyExistsException() {
        super(BizConstants.USER_ALREADY_EXISTS);
    }

    @Nonnull
    @Override
    public ResponseEntity<Error> handleError() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Error(getMessage()));
    }
}
