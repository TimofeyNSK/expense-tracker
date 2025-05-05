package ru.zarubin.expensetracker.controller;

import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zarubin.expensetracker.dto.CategoryCreateDTO;
import ru.zarubin.expensetracker.dto.CategoryDTO;
import ru.zarubin.expensetracker.dto.CategoryUpdateDTO;
import ru.zarubin.expensetracker.enums.CategoryType;
import ru.zarubin.expensetracker.service.CategoryService;

import java.util.List;


@RestController
@RequestMapping("/categories")
@Data
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get_all")
   public List<CategoryDTO> getAll(){
        return categoryService.getAll();
    }
    @PostMapping
    public CategoryDTO saveCategory(@Valid @RequestBody CategoryCreateDTO category){
        return categoryService.saveCategory(category);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCategoryByName(@Valid @RequestBody Long id){
        categoryService.deleteCategory(id);//todo Обновить запрос Postman
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/name")
    public CategoryDTO findByName(@Valid @RequestParam String name){
        return categoryService.findByName(name);
    }
    @GetMapping("/type")
    public List<CategoryDTO> findByType(@Valid @RequestParam CategoryType type){
        return categoryService.findByType(type);
    }
    @PutMapping("/update")
    public CategoryDTO updateCategory(@Valid @RequestBody CategoryUpdateDTO updateCategory){
        return categoryService.updateCategory(updateCategory);
    }

}
