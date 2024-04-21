package io.hikarilan.anotherlibrarymanagementsystem.app.repository;

import io.hikarilan.anotherlibrarymanagementsystem.app.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbnNumber(long isbnNumber);

    // find if the book is already borrowed by the user
    boolean existsByBorrowRecordsUserIdAndBorrowRecordsBookId(long borrowRecords_user_id, long borrowRecords_book_id);

}
