package io.hikarilan.anotherlibrarymanagementsystem.app.exception;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.BizConstants;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.Error;
import jakarta.annotation.Nonnull;
import org.springframework.http.ResponseEntity;

public class BookNoNeedToReturnException extends BusinessException {

    public BookNoNeedToReturnException() {
        super(BizConstants.BOOK_NO_NEED_TO_RETURN);
    }

    @Nonnull
    @Override
    public ResponseEntity<Error> handleError() {
        return null;
    }
}
