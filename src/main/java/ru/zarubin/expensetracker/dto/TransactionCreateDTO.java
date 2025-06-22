package ru.zarubin.expensetracker.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.time.LocalDate;

@Data
public class TransactionCreateDTO {
    @NotBlank(message = "A transaction cannot be without a name ")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;
    @DecimalMin(value = "0", message = "Amount be greater than 0")
    @NotNull(message = "A transaction cannot be without a amount")
    private Double amount;
    @NotNull(message = "A transaction cannot be without a date of purchase")
    private LocalDate dateOfPurchase;
@NotNull(message = "A transaction cannot be without a category")
    private Long categoryId;
}
