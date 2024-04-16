package io.hikarilan.anotherlibrarymanagementsystem.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "borrow_date", nullable = false)
    private Date borrowDate;

    @Column(name = "return_date")
    @Setter
    private Date returnDate;
}
