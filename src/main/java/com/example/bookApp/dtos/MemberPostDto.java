package com.example.bookApp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberPostDto(@NotBlank String name,
                            @NotBlank @Email String email,
                            @NotBlank String password) {
}
