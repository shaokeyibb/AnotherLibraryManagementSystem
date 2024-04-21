package io.hikarilan.anotherlibrarymanagementsystem.app.exception;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.BizConstants;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.Error;
import jakarta.annotation.Nonnull;
import org.springframework.http.ResponseEntity;

public class BookNoCopiesAvailableException extends BusinessException {

    public BookNoCopiesAvailableException() {
        super(BizConstants.BOOK_NO_COPIES_AVAILABLE);
    }

    @Nonnull
    @Override
    public ResponseEntity<Error> handleError() {
        return ResponseEntity.badRequest().body(new Error(getMessage()));
    }
}
