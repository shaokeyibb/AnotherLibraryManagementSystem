package io.hikarilan.anotherlibrarymanagementsystem.app.data.vo;

import io.hikarilan.anotherlibrarymanagementsystem.app.entity.BorrowRecord;

import java.util.Optional;

public record BorrowRecordVo(
        long id,
        long userId,
        long bookId,
        String borrowDate,
        String returnDate
) {

    public static BorrowRecordVo fromEntity(BorrowRecord borrowRecord) {
        return new BorrowRecordVo(
                borrowRecord.getId(),
                borrowRecord.getUser().getId(),
                borrowRecord.getBook().getId(),
                borrowRecord.getBorrowDate().toString(),
                Optional.ofNullable(borrowRecord.getReturnDate()).map(Object::toString).orElse(null)
        );
    }

}
