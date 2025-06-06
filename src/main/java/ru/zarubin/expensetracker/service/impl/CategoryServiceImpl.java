package ru.zarubin.expensetracker.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


@Slf4j
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
        log.info("categoryDTO: {},name: {}, categoryType: {}", categoryDTO,categoryDTO.getName(),categoryDTO.getCategoryType());
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
    public List<CategoryDTO> findByCategoryType(CategoryType categoryType) {
        log.info("categoryType: {}", categoryType);
        List<Category> categories = repository.findByCategoryType(categoryType);
        if (categories.isEmpty()) {
            throw new CategoryTypeNotFoundException("Category type: " + categoryType + " not found");
        }

        return mapper.toDTOList(categories);
    }

    @Override
    public CategoryDTO updateCategory(CategoryUpdateDTO updateCategory) {
        log.info("id {},name: {}, categoryType: {}", updateCategory.getId(), updateCategory.getName(), updateCategory.getCategoryType());
        Category category = mapper.toUpdateEntity(updateCategory);
        log.info("id: {},name: {}, categoryType: {}", category.getId(), category.getName(), category.getCategoryType());
        Category oldCategory = repository.findById(category
                .getId())
                .orElseThrow(() -> new CategoryNotFoundException("Category: " + updateCategory.toString() + " not found"));
        log.info("id: {},name: {}, categoryType: {}", oldCategory.getId(), oldCategory.getName(), oldCategory.getCategoryType());
        oldCategory.setName(category.getName());
        oldCategory.setCategoryType(category.getCategoryType());
        log.info("id: {},name: {}, categoryType: {}", oldCategory.getId(), oldCategory.getName(), oldCategory.getCategoryType());
        repository.save(oldCategory);
        log.info("id {},name: {}, categoryType: {}",oldCategory.getId(), oldCategory.getName(), oldCategory.getCategoryType());
        return mapper.toDTO(oldCategory);
    }
}
