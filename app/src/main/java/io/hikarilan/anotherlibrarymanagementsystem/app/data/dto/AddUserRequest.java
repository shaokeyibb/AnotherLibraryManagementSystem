package io.hikarilan.anotherlibrarymanagementsystem.app.data.dto;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.Role;
import jakarta.annotation.Nonnull;

public record AddUserRequest(
        @Nonnull String username,
        @Nonnull String password,
        Role role
) {
}
