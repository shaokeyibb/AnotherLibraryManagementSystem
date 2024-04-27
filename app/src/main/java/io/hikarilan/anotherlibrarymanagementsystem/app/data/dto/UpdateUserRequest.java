package io.hikarilan.anotherlibrarymanagementsystem.app.data.dto;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.Role;
import jakarta.annotation.Nonnull;

public record UpdateUserRequest(
        @Nonnull String username,
        @Nonnull String password,
        Role role
) {
}
