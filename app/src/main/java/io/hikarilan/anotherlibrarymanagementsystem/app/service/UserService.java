package io.hikarilan.anotherlibrarymanagementsystem.app.service;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.Role;
import io.hikarilan.anotherlibrarymanagementsystem.app.entity.User;
import io.hikarilan.anotherlibrarymanagementsystem.app.repository.UserRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getUser(@Nonnull String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(@Nonnull String username, @Nonnull String hashedPassword) {
        return createUser(username, hashedPassword, Role.USER);
    }

    public User createUser(@Nonnull String username, @Nonnull String hashedPassword, @Nonnull Role role) {
        return userRepository.save(User.builder()
                .username(username)
                .password(hashedPassword)
                .role(role)
                .build());
    }

    public void updateUser(@Nonnull User user) {
        userRepository.save(user);
    }

}
