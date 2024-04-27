package io.hikarilan.anotherlibrarymanagementsystem.app.data.dto;

import jakarta.annotation.Nonnull;

public record ForgetPasswordRequest(@Nonnull String username,
                                    @Nonnull String captcha) { // for simulation only, not used in the actual implementation
}
