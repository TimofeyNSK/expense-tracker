package ru.zarubin.expensetracker.controller;

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
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get_all")
   public List<CategoryDTO> getAll(){
        return categoryService.getAll();
    }
    @PostMapping("/save")
    public CategoryDTO saveCategory(@Valid @RequestBody CategoryCreateDTO category){
        log.info("name: {},categoryType: {}", category.getName(),category.getCategoryType());
        return categoryService.saveCategory(category);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCategoryById(@Valid @RequestBody Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/name")
    public CategoryDTO findByName(@NotBlank @RequestParam String name){
        return categoryService.findByName(name);
    }
    @GetMapping("/categoryType")
    public List<CategoryDTO> findByType(@RequestParam CategoryType categoryType){
        log.info("categoryType: {}", categoryType);
        return categoryService.findByCategoryType(categoryType);
    }
    @PutMapping("/update")
    public CategoryDTO updateCategory(@Valid @RequestBody CategoryUpdateDTO updateCategory){
        log.info("id {},name {},categoryType {}", updateCategory.getId(), updateCategory.getName(), updateCategory.getCategoryType());
        return categoryService.updateCategory(updateCategory);
    }

}
