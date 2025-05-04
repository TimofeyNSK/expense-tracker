package ru.zarubin.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

public class TransactionCreateDTO {
    @NotBlank(message = "A transaction cannot be without a name ")
    private String name;
    @NotNull(message = "A transaction cannot be without a amount")
    private Double amount;
    @NotNull(message = "A transaction cannot be without a date of purchase")
    private LocalDate dateOfPurchase;
    @NotNull(message = "A transaction cannot be without a category")
    private CategoryDTO category;
}
