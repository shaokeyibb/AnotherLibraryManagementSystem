package io.hikarilan.anotherlibrarymanagementsystem.app.data.vo;

import io.hikarilan.anotherlibrarymanagementsystem.app.entity.Book;

import java.util.List;

public record BookVo(
        long id,
        long isbnNumber,
        String title,
        String author,
        String publisher,
        int numberOfCopies,
        List<BorrowRecordVo> borrowRecords) {

    public static BookVo fromEntity(Book book) {
        return new BookVo(
                book.getId(),
                book.getIsbnNumber(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getNumberOfCopies(),
                book.getBorrowRecords().stream().map(BorrowRecordVo::fromEntity).toList()
        );
    }

}
