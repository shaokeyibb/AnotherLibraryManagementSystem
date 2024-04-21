package io.hikarilan.anotherlibrarymanagementsystem.app.exception;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.BizConstants;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.Error;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class InvalidUsernameOrPasswordException extends BusinessException {

    public InvalidUsernameOrPasswordException() {
        super(BizConstants.INVALID_USERNAME_OR_PASSWORD);
    }

    @Nonnull
    @Override
    public ResponseEntity<Error> handleError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Error(getMessage()));
    }
}
