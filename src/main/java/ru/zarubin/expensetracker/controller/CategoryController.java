package ru.zarubin.expensetracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zarubin.expensetracker.dto.CategoryCreateDTO;
import ru.zarubin.expensetracker.dto.CategoryDTO;
import ru.zarubin.expensetracker.dto.CategoryUpdateDTO;
import ru.zarubin.expensetracker.enums.CategoryType;
import ru.zarubin.expensetracker.service.CategoryService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/categories")
@Data
@Tag(name = "Categories", description = "Управление категориями")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Получить все категории", description = "Возвращает список всех категорий")
    @GetMapping("/get_all")
    public List<CategoryDTO> getAll() {
        return categoryService.getAll();
    }

    @Operation(summary = "Сохранить категорию", description = "Сохраняет категорию")
    @PostMapping("/save")
    public CategoryDTO saveCategory(@Parameter(name = "CategoryCreateDTO",
            description = "Категория со всеми полями кроме id",
            required = true)
                                    @Valid @RequestBody CategoryCreateDTO categoryCreateDTO) {
        log.info("name: {},categoryType: {}", categoryCreateDTO.getName(), categoryCreateDTO.getCategoryType());
        return categoryService.saveCategory(categoryCreateDTO);
    }

    @Operation(summary = "Удаление категории", description = "Удаляет категорию по id")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCategoryById(@Parameter(name = "id", description = "id", required = true)
                                                   @Valid @RequestBody Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Поиск категории по имени", description = "Возвращает категорию из списка по имени")
    @GetMapping("/name")
    public CategoryDTO findByName(@Parameter(name = "name", description = "Имя, не быть пустым или null", required = true)
                                  @NotBlank @RequestParam String name) {
        return categoryService.findByName(name);
    }
    @Operation(summary = "Поиск по типу",description = "Возвращает список категории по типу")
    @GetMapping("/categoryType")
    public List<CategoryDTO> findByType(@Parameter(name = "categoryType",description = "Может быть INCOME, EXPENSE,INVESTMENT",required = true)
                                            @RequestParam CategoryType categoryType) {
        log.info("categoryType: {}", categoryType);
        return categoryService.findByCategoryType(categoryType);
    }
    @Operation(summary = "Обновление",description = "Обновляет категорию")
    @PutMapping("/update")
    public CategoryDTO updateCategory(@Parameter(name = "updateCategory",required = true)@Valid @RequestBody CategoryUpdateDTO updateCategory) {
        log.info("id {},name {},categoryType {}", updateCategory.getId(), updateCategory.getName(), updateCategory.getCategoryType());
        return categoryService.updateCategory(updateCategory);
    }

}
