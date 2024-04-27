package io.hikarilan.anotherlibrarymanagementsystem.app.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.UserVo;
import io.hikarilan.anotherlibrarymanagementsystem.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @SaCheckLogin
    @GetMapping
    public ResponseEntity<UserVo> getUser() {
        return ResponseEntity.of(userService.getUser(StpUtil.getLoginIdAsLong()).map(UserVo::fromEntity));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserVo> getUser(@PathVariable String username) {
        return ResponseEntity.of(userService.getUser(username).map(UserVo::fromEntity));
    }

    @SaCheckRole("ADMIN")
    @GetMapping("/all")
    public List<UserVo> getAllUsers() {
        var users = userService.getUsers();

        return users.stream().map(UserVo::fromEntity).toList();
    }

}
