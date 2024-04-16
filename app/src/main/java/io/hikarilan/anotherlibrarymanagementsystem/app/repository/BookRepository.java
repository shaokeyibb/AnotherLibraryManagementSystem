package io.hikarilan.anotherlibrarymanagementsystem.app.repository;

import io.hikarilan.anotherlibrarymanagementsystem.app.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
