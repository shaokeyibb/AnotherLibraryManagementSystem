package io.hikarilan.anotherlibrarymanagementsystem.app.data.dto;

import jakarta.annotation.Nonnull;

public record SignInRequest(
        @Nonnull String username,
        @Nonnull String password
) {
}
