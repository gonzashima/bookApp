package com.example.bookApp.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthorDto(@NotBlank String name) {
}
