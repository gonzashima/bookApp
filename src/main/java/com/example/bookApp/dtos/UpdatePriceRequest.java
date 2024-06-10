package com.example.bookApp.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdatePriceRequest(@NotNull @Positive BigDecimal price) {
}