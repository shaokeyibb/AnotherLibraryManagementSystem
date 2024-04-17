package io.hikarilan.anotherlibrarymanagementsystem.app.repository;

import io.hikarilan.anotherlibrarymanagementsystem.app.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbnNumber(long isbnNumber);

}
