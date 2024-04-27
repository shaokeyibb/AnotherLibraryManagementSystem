package io.hikarilan.anotherlibrarymanagementsystem.app.service;

import io.hikarilan.anotherlibrarymanagementsystem.app.entity.Book;
import io.hikarilan.anotherlibrarymanagementsystem.app.exception.BookNotExistsException;
import io.hikarilan.anotherlibrarymanagementsystem.app.repository.BookRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Optional<Book> getBook(long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getBooks(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public void addBook(long isbnNumber,
                        @Nonnull String title,
                        String author,
                        String publisher,
                        int numberOfCopies) {
        saveBook(Book.builder()
                .isbnNumber(isbnNumber)
                .title(title)
                .author(author)
                .publisher(publisher)
                .numberOfCopies(numberOfCopies)
                .build());
    }

    public void updateBook(long id,
                           long isbnNumber,
                           @Nonnull String title,
                           String author,
                           String publisher,
                           int numberOfCopies) {
        if (getBook(id).isEmpty()) {
            throw new BookNotExistsException();
        }

        saveBook(Book.builder()
                .id(id)
                .isbnNumber(isbnNumber)
                .title(title)
                .author(author)
                .publisher(publisher)
                .numberOfCopies(numberOfCopies)
                .build());
    }

    public void deleteBook(long id) {
        if (getBook(id).isEmpty()) {
            throw new BookNotExistsException();
        }

        bookRepository.deleteById(id);
    }

    private void saveBook(Book book) {
        bookRepository.save(book);
    }

}
