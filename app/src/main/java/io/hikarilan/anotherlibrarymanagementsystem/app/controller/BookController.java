package io.hikarilan.anotherlibrarymanagementsystem.app.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.dto.AddBookRequest;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.dto.UpdateBookRequest;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.BookVo;
import io.hikarilan.anotherlibrarymanagementsystem.app.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{bookId}")
    public ResponseEntity<BookVo> getBook(@PathVariable long bookId) {
        var book = bookService.getBook(bookId);

        return ResponseEntity.of(book.map(BookVo::fromEntity));
    }

    @GetMapping
    public List<BookVo> getBooks(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "" + Integer.MAX_VALUE) int size) {
        var books = bookService.getBooks(page, size);

        return books.stream().map(BookVo::fromEntity).toList();
    }

    @SaCheckRole("ADMIN")
    @PostMapping
    public void addBook(@Validated @RequestBody AddBookRequest request) {
        bookService.addBook(request.isbnNumber(), request.title(), request.author(), request.publisher(), request.numberOfCopies());
    }

    @SaCheckRole("ADMIN")
    @PutMapping("/{bookId}")
    public void updateBook(@PathVariable long bookId, @Validated @RequestBody UpdateBookRequest request) {
        bookService.updateBook(bookId, request.isbnNumber(), request.title(), request.author(), request.publisher(), request.numberOfCopies());
    }

    @SaCheckRole("ADMIN")
    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable long bookId) {
        bookService.deleteBook(bookId);
    }

}
