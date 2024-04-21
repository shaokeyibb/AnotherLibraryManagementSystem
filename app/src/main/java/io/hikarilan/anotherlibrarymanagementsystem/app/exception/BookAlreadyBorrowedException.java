package io.hikarilan.anotherlibrarymanagementsystem.app.exception;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.Error;
import jakarta.annotation.Nonnull;
import org.springframework.http.ResponseEntity;

public class BookAlreadyBorrowedException extends BusinessException {

    public BookAlreadyBorrowedException() {
        super("Book already borrowed");
    }


    @Nonnull
    @Override
    public ResponseEntity<Error> handleError() {
        return ResponseEntity.badRequest().body(new Error(getMessage()));
    }
}
