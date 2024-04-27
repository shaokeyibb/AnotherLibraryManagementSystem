package io.hikarilan.anotherlibrarymanagementsystem.app.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.dto.AddUserRequest;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.dto.UpdateUserRequest;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.UserVo;
import io.hikarilan.anotherlibrarymanagementsystem.app.entity.User;
import io.hikarilan.anotherlibrarymanagementsystem.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
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

    @SaCheckRole("ADMIN")
    @PostMapping
    public void addBook(@Validated @RequestBody AddUserRequest request) {
        userService.createUser(request.username(), BCrypt.hashpw(request.password()), request.role());
    }

    @SaCheckRole("ADMIN")
    @PutMapping("/{userId}")
    public void updateBook(@PathVariable long userId, @Validated @RequestBody UpdateUserRequest request) {
        userService.updateUser(User.builder()
                .id(userId)
                .username(request.username())
                .password(BCrypt.hashpw(request.password()))
                .role(request.role())
                .build());
    }

    @SaCheckRole("ADMIN")
    @DeleteMapping("/{userId}")
    public void deleteBook(@PathVariable long userId) {
        userService.deleteUser(userId);
    }

}
