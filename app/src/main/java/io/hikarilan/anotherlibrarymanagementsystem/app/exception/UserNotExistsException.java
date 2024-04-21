package io.hikarilan.anotherlibrarymanagementsystem.app.exception;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.BizConstants;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.Error;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserNotExistsException extends BusinessException {

    public UserNotExistsException() {
        super(BizConstants.USER_NOT_EXISTS);
    }

    @Nonnull
    @Override
    public ResponseEntity<Error> handleError() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(getMessage()));
    }
}
