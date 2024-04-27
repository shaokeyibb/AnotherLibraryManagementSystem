package io.hikarilan.anotherlibrarymanagementsystem.app.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import io.hikarilan.anotherlibrarymanagementsystem.app.service.BorrowService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/book/{bookId}")
public class BorrowController {

    private final BorrowService borrowService;

    @SaCheckLogin
    @PostMapping("/action/borrow")
    public void borrowBook(@PathVariable long bookId) {
        var userId = StpUtil.getLoginIdAsLong();
        borrowService.borrowBook(userId, bookId);
    }

    @SaCheckLogin
    @PostMapping("/action/return")
    public void returnBook(@PathVariable long bookId) {
        var userId = StpUtil.getLoginIdAsLong();
        borrowService.returnBook(userId, bookId);
    }

}
