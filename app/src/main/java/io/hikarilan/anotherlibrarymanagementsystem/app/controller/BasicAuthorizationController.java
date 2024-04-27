package io.hikarilan.anotherlibrarymanagementsystem.app.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.dto.ChangePasswordRequest;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.dto.ForgetPasswordRequest;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.dto.SignInRequest;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.dto.SignUpRequest;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.vo.UserVo;
import io.hikarilan.anotherlibrarymanagementsystem.app.service.BasicAuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@AllArgsConstructor
@RestController
@RequestMapping("/api/authorization")
public class BasicAuthorizationController {

    private final BasicAuthorizationService basicAuthorizationService;

    @PostMapping("/sign-in")
    public UserVo signIn(@Validated @RequestBody SignInRequest request) {
        return UserVo.fromEntity(basicAuthorizationService.signIn(request.username(), request.password()));
    }

    @PostMapping("/sign-up")
    public UserVo signUp(@Validated @RequestBody SignUpRequest request) {
        return UserVo.fromEntity(basicAuthorizationService.signUp(request.username(), request.password()));
    }

    @SaCheckLogin
    @PostMapping("/sign-out")
    public void signOut() {
        basicAuthorizationService.signOut();
    }

    @SaCheckLogin
    @PostMapping("/change-password")
    public void changePassword(@Validated @RequestBody ChangePasswordRequest request) {
        basicAuthorizationService.changePassword(StpUtil.getLoginIdAsLong(), request.password());
    }

    @PostMapping("/forget-password")
    public String forgetPassword(@Validated @RequestBody ForgetPasswordRequest request) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String newPassword = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        basicAuthorizationService.changePassword(request.username(), newPassword);

        return newPassword;
    }

}
