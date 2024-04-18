package io.hikarilan.anotherlibrarymanagementsystem.app.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.dto.ChangePasswordRequest;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.dto.SignInRequest;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.dto.SignUpRequest;
import io.hikarilan.anotherlibrarymanagementsystem.app.service.BasicAuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/authorization")
public class BasicAuthorizationController {

    private final BasicAuthorizationService basicAuthorizationService;

    @PostMapping("/sign-in")
    public void signIn(@Validated @RequestBody SignInRequest request) {
        basicAuthorizationService.signIn(request.username(), request.password());
    }

    @PostMapping("/sign-up")
    public void signUp(@Validated @RequestBody SignUpRequest request) {
        basicAuthorizationService.signUp(request.username(), request.password());
    }

    @SaCheckLogin
    @PostMapping("/sign-out")
    public void signOut() {
        basicAuthorizationService.signOut();
    }

    @SaCheckLogin
    @PostMapping("/change-password")
    public void changePassword(@Validated @RequestBody ChangePasswordRequest request) {
        basicAuthorizationService.changePassword(request.password());
    }

}
