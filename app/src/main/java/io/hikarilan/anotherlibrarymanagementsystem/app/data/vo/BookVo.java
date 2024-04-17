package io.hikarilan.anotherlibrarymanagementsystem.app.data.vo;

public record BookVo(
        long isbnNumber,
        String title,
        String author,
        String publisher,
        int numberOfCopies) {

    public static BookVo fromEntity(io.hikarilan.anotherlibrarymanagementsystem.app.entity.Book book) {
        return new BookVo(
                book.getIsbnNumber(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getNumberOfCopies()
        );
    }

}
