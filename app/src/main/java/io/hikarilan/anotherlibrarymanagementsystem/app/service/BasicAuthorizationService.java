package io.hikarilan.anotherlibrarymanagementsystem.app.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import io.hikarilan.anotherlibrarymanagementsystem.app.entity.User;
import io.hikarilan.anotherlibrarymanagementsystem.app.exception.InvalidUsernameOrPasswordException;
import io.hikarilan.anotherlibrarymanagementsystem.app.exception.UserAlreadyExistsException;
import io.hikarilan.anotherlibrarymanagementsystem.app.exception.UserNotExistsException;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BasicAuthorizationService {

    private final UserService userService;

    public User signIn(@Nonnull String username, @Nonnull String password) {
        var user = userService.getUser(username)
                .filter(it -> BCrypt.checkpw(password, it.getPassword()))
                .orElseThrow(InvalidUsernameOrPasswordException::new);

        StpUtil.login(user.getId());

        return user;
    }

    public User signUp(@Nonnull String username, @Nonnull String password) {
        userService.getUser(username).ifPresent(it -> {
            throw new UserAlreadyExistsException();
        });

        var user = userService.createUser(username, BCrypt.hashpw(password));

        StpUtil.login(user.getId());

        return user;
    }

    public void signOut() {
        StpUtil.logout();
    }

    public void changePassword(long userId, @Nonnull String password) {
        var user = userService.getUser(userId);

        if (user.isEmpty()) {
            throw new UserNotExistsException();
        }

        changePassword(user.get(), password);
    }

    public void changePassword(String username, @Nonnull String password) {
        var user = userService.getUser(username);

        if (user.isEmpty()) {
            throw new UserNotExistsException();
        }

        changePassword(user.get(), password);
    }

    private void changePassword(@Nonnull User user, @Nonnull String password) {
        userService.updateUser(user.toBuilder()
                .password(BCrypt.hashpw(password))
                .build());
    }

}
