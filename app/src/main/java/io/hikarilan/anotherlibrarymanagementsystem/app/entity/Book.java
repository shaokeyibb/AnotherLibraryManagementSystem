package io.hikarilan.anotherlibrarymanagementsystem.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books", indexes = {
        @Index(name = "idx_book_isbn_number", columnList = "isbn_number"),
        @Index(name = "idx_book_title", columnList = "title"),
        @Index(name = "idx_book_author", columnList = "author"),
        @Index(name = "idx_book_publisher", columnList = "publisher")
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "isbn_number", nullable = false)
    private long isbnNumber;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Builder.Default
    @Column(name = "number_of_copies", nullable = false)
    private int numberOfCopies = 0;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BorrowRecord> borrowRecords;
}
