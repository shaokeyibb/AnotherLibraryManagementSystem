package io.hikarilan.anotherlibrarymanagementsystem.app.exception;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.Error;
import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    @Nonnull
    abstract public ResponseEntity<Error> handleError();

}
