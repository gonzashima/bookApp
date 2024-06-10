package com.example.bookApp.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookDto(@NotBlank String title,
                      @NotNull AuthorDto author,
                      @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate publishedDate,
                      @NotNull @Positive BigDecimal price) {
}
