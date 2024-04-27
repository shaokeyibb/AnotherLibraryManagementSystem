package io.hikarilan.anotherlibrarymanagementsystem.app.service;

import io.hikarilan.anotherlibrarymanagementsystem.app.entity.BorrowRecord;
import io.hikarilan.anotherlibrarymanagementsystem.app.exception.*;
import io.hikarilan.anotherlibrarymanagementsystem.app.repository.BookRepository;
import io.hikarilan.anotherlibrarymanagementsystem.app.repository.BorrowRecordRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class BorrowService {

    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookService bookService;
    private final UserService userService;

    @Transactional
    public void borrowBook(long userId, long bookId) {
        var _book = bookService.getBook(bookId);
        if (_book.isEmpty()) {
            throw new BookNotExistsException();
        }
        var book = _book.get();

        var _user = userService.getUser(userId);
        if (_user.isEmpty()) {
            throw new UserNotExistsException();
        }
        var user = _user.get();

        if (book.getNumberOfCopies() == 0) {
            throw new BookNoCopiesAvailableException();
        }

        if (borrowRecordRepository.existsByUserIdAndBookIdAndReturnDateIsNull(userId, bookId)) {
            throw new BookAlreadyBorrowedException();
        }

        var borrowRecord = BorrowRecord.builder()
                .book(book)
                .user(user)
                .borrowDate(new Date())
                .build();
        borrowRecordRepository.save(borrowRecord);

        bookRepository.save(book);
    }

    @Transactional
    public void returnBook(long userId, long bookId) {
        var _book = bookService.getBook(bookId);
        if (_book.isEmpty()) {
            throw new BookNotExistsException();
        }
        var book = _book.get();

        var _user = userService.getUser(userId);
        if (_user.isEmpty()) {
            throw new UserNotExistsException();
        }

        var _borrowRecord = borrowRecordRepository.findByUserIdAndBookIdAndReturnDateIsNull(userId, bookId);
        if (_borrowRecord.isEmpty()) {
            throw new BookNoNeedToReturnException();
        }
        var borrowRecord = _borrowRecord.get().toBuilder()
                .returnDate(new Date())
                .build();

        borrowRecordRepository.save(borrowRecord);

        bookRepository.save(book);
    }

}
