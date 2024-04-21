package io.hikarilan.anotherlibrarymanagementsystem.app.exception;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.BizConstants;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.Error;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BookNotExistsException extends BusinessException {

    public BookNotExistsException() {
        super(BizConstants.BOOK_NOT_EXISTS);
    }


    @Nonnull
    @Override
    public ResponseEntity<Error> handleError() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(getMessage()));
    }
}
