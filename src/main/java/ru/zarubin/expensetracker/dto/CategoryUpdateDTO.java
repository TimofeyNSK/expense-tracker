package ru.zarubin.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.zarubin.expensetracker.enums.CategoryType;

public class CategoryUpdateDTO {
    @NotNull
    private Long id;
    @NotBlank(message="A category cannot be without a name")
    private String name;
    @NotNull(message ="A category cannot be without a category type")
    private CategoryType categoryType;
}
