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
@Table(indexes = {
        @Index(name = "idx_book_title", columnList = "title"),
        @Index(name = "idx_book_author", columnList = "author"),
        @Index(name = "idx_book_publisher", columnList = "publisher")
})
public class Book {

    @Id
    @Column(name = "id", nullable = false)
    private Long isbnNumber;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "number_of_copies", nullable = false)
    private Integer numberOfCopies = 0;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BorrowRecord> borrowRecords;
}
