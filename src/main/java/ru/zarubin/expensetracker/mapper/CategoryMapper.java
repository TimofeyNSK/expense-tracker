package ru.zarubin.expensetracker.mapper;

import org.mapstruct.Mapper;
import ru.zarubin.expensetracker.dto.CategoryCreateDTO;
import ru.zarubin.expensetracker.dto.CategoryDTO;
import ru.zarubin.expensetracker.dto.CategoryUpdateDTO;
import ru.zarubin.expensetracker.model.Category;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);//Из сущности в дто

    Category toEntity(CategoryDTO categoryDTO);//Из дто в сущность

    List<CategoryDTO> toDTOList(List<Category> categoryList);//из сущности списка в список дто

    CategoryCreateDTO toCreateDTO(Category category);

    CategoryUpdateDTO toUpdateDTO(Category category);

    Category toUpdateEntity(CategoryUpdateDTO categoryUpdateDTO);

    Category toCreateEntity(CategoryCreateDTO categoryCreateDTO);


}
