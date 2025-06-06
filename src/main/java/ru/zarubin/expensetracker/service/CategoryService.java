package ru.zarubin.expensetracker.service;


import ru.zarubin.expensetracker.dto.CategoryCreateDTO;
import ru.zarubin.expensetracker.dto.CategoryDTO;
import ru.zarubin.expensetracker.dto.CategoryUpdateDTO;
import ru.zarubin.expensetracker.enums.CategoryType;

import java.util.List;


public interface CategoryService {
    List<CategoryDTO> getAll();

    CategoryDTO saveCategory(CategoryCreateDTO category);

    void deleteCategory(Long id);

    CategoryDTO findByName(String name);

    List<CategoryDTO> findByCategoryType(CategoryType categoryType);

    CategoryDTO updateCategory(CategoryUpdateDTO updateCategory);

}
