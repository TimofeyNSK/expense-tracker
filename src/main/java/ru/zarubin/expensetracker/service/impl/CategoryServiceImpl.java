package ru.zarubin.expensetracker.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zarubin.expensetracker.dto.CategoryCreateDTO;
import ru.zarubin.expensetracker.dto.CategoryDTO;
import ru.zarubin.expensetracker.dto.CategoryUpdateDTO;
import ru.zarubin.expensetracker.enums.CategoryType;
import ru.zarubin.expensetracker.exception.CategoryNotFoundException;
import ru.zarubin.expensetracker.exception.CategoryTypeNotFoundException;
import ru.zarubin.expensetracker.mapper.CategoryMapper;
import ru.zarubin.expensetracker.model.Category;
import ru.zarubin.expensetracker.repository.CategoryRepository;
import ru.zarubin.expensetracker.service.CategoryService;
import java.util.List;


@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categories = repository.findAll();
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("There are no categories");
        }
        return mapper.toDTOList(categories);
    }

    @Override
    public CategoryDTO saveCategory(CategoryCreateDTO categoryDTO) {
        Category category = mapper.toCreateEntity(categoryDTO);
        Category savedCategory = repository.save(category);
        return mapper.toDTO(savedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CategoryDTO findByName(String name) {
        Category category = repository.findByName(name).orElseThrow(() -> new CategoryNotFoundException("Category with name: \"" + name + "\" not found"));
        return mapper.toDTO(category);
    }

    @Override
    public List<CategoryDTO> findByType(CategoryType type) {
        List<Category> categories = repository.findByType(type);
        if (categories.isEmpty()) {
            throw new CategoryTypeNotFoundException("Category type: " + type + " not found");
        }

        return mapper.toDTOList(categories);
    }

    @Override
    public CategoryDTO updateCategory(CategoryUpdateDTO updateCategory) {
        Category category = mapper.toUpdateEntity(updateCategory);
        Category oldCategory = repository.findById(category
                .getId())
                .orElseThrow(() -> new CategoryNotFoundException("Category: " + updateCategory.toString() + " not found"));
        oldCategory.setName(category.getName());
        oldCategory.setType(category.getType());
        repository.save(oldCategory);
        return mapper.toDTO(oldCategory);
    }
}
