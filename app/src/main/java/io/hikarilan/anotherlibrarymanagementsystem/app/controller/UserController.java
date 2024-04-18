package io.hikarilan.anotherlibrarymanagementsystem.app.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.UserVo;
import io.hikarilan.anotherlibrarymanagementsystem.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public ResponseEntity<UserVo> getUser(@PathVariable long id) {
        return ResponseEntity.of(userService.getUser(id).map(UserVo::fromEntity));
    }

}
