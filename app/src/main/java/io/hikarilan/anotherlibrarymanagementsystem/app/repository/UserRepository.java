package io.hikarilan.anotherlibrarymanagementsystem.app.repository;

import io.hikarilan.anotherlibrarymanagementsystem.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
