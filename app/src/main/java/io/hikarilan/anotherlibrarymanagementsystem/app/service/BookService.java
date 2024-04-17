package io.hikarilan.anotherlibrarymanagementsystem.app.service;

import io.hikarilan.anotherlibrarymanagementsystem.app.entity.Book;
import io.hikarilan.anotherlibrarymanagementsystem.app.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Optional<Book> getBook(long isbnNumber) {
        return bookRepository.findByIsbnNumber(isbnNumber);
    }

    public List<Book> getBooks(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size)).getContent();
    }

}
