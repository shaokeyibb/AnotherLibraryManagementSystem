package io.hikarilan.anotherlibrarymanagementsystem.app.data.dto;

import jakarta.annotation.Nonnull;

public record SignUpRequest(
        @Nonnull String username,
        @Nonnull String password
) {
}
