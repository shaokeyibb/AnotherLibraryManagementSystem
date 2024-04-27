package io.hikarilan.anotherlibrarymanagementsystem.app.configuration;

import cn.dev33.satoken.secure.BCrypt;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.Role;
import io.hikarilan.anotherlibrarymanagementsystem.app.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class DefaultAdminUserAutoConfiguration {

    @Value("${app.admin.username:'admin'}")
    private String adminUsername;

    @Value("${app.admin.password:'admin'}")
    private String adminPassword;

    private final UserService userService;

    @PostConstruct
    public void createAdminUserIfNotExists() {
        if (userService.getUser(adminUsername).isPresent()) return;
        log.info("Creating default admin user with username: {}, password: {}", adminUsername, adminPassword);
        userService.createUser(adminUsername, BCrypt.hashpw(adminPassword), Role.ADMIN);
    }

}
