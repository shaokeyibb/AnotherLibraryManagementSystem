package io.hikarilan.anotherlibrarymanagementsystem.app.data.dto;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public record AddBookRequest(long isbnNumber,
                             @Nonnull String title,
                             @Nullable String author,
                             @Nullable String publisher,
                             int numberOfCopies) {
}