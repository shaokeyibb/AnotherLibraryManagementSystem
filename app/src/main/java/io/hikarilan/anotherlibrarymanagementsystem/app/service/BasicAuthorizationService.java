package io.hikarilan.anotherlibrarymanagementsystem.app.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import io.hikarilan.anotherlibrarymanagementsystem.app.entity.User;
import io.hikarilan.anotherlibrarymanagementsystem.app.exception.InvalidUsernameOrPasswordException;
import io.hikarilan.anotherlibrarymanagementsystem.app.exception.UserAlreadyExistsException;
import io.hikarilan.anotherlibrarymanagementsystem.app.repository.UserRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BasicAuthorizationService {

    private final UserRepository userRepository;

    public void signIn(@Nonnull String username, @Nonnull String password) {
        var user = userRepository.findByUsername(username)
                .filter(it -> BCrypt.checkpw(password, it.getPassword()))
                .orElseThrow(InvalidUsernameOrPasswordException::new);

        StpUtil.login(user.getId());
    }

    public void signUp(@Nonnull String username, @Nonnull String password) {
        userRepository.findByUsername(username).ifPresent(it -> {
            throw new UserAlreadyExistsException();
        });

        var user = userRepository.save(User.builder()
                .username(username)
                .password(BCrypt.hashpw(password))
                .build());

        StpUtil.login(user.getId());
    }

    public void signOut() {
        StpUtil.logout();
    }

}
