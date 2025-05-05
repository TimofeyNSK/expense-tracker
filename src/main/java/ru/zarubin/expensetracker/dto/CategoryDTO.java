package ru.zarubin.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.zarubin.expensetracker.enums.CategoryType;
@Data
public class CategoryDTO {
    @NotNull
    private Long id;
    @NotBlank(message="A category cannot be without a name")
    @Size(max = 50,message = "Name must be at most 50 characters")
    private String name;
    @NotNull(message ="A category cannot be without a category type")
    private CategoryType categoryType;
}