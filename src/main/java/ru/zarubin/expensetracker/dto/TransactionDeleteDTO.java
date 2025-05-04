package ru.zarubin.expensetracker.dto;

import jakarta.validation.constraints.NotNull;

public class TransactionDeleteDTO {
    @NotNull
    private Long id;
}
