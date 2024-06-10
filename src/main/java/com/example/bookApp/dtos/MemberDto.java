package com.example.bookApp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberDto(@NotBlank String name,
                        @Email String email) {
}
