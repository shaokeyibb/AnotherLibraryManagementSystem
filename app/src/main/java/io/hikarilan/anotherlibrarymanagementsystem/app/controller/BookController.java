package io.hikarilan.anotherlibrarymanagementsystem.app.controller;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.BookVo;
import io.hikarilan.anotherlibrarymanagementsystem.app.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/book")
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

}
