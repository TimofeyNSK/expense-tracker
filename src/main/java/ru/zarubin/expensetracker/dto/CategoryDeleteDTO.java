package ru.zarubin.expensetracker.dto;

import jakarta.validation.constraints.NotNull;

public class CategoryDeleteDTO {
    @NotNull
    private Long id;
}
