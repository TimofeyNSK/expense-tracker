package ru.zarubin.expensetracker.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;
@Data
public class TransactionUpdateDTO {
    @NotNull
    private Long id;
    @NotBlank(message = "A transaction cannot be without a name ")
    @Size(max = 50,message = "Name must be at most 50 characters")
    private String name;
    @NotNull(message = "A transaction cannot be without a amount")
    @DecimalMin(value = "0.0000000001",message = "Amount be greater than 0")
    private Double amount;
    @NotNull(message = "A transaction cannot be without a date of purchase")
    private LocalDate dateOfPurchase;
    @NotNull(message = "A transaction cannot be without a category")
    private CategoryDTO category;
}
