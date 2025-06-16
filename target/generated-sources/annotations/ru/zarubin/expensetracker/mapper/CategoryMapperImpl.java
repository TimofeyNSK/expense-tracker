package ru.zarubin.expensetracker.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.zarubin.expensetracker.dto.CategoryCreateDTO;
import ru.zarubin.expensetracker.dto.CategoryDTO;
import ru.zarubin.expensetracker.dto.CategoryUpdateDTO;
import ru.zarubin.expensetracker.model.Category;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-07T12:33:35+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setCategoryType( category.getCategoryType() );

        return categoryDTO;
    }

    @Override
    public Category toEntity(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );
        category.setCategoryType( categoryDTO.getCategoryType() );

        return category;
    }

    @Override
    public List<CategoryDTO> toDTOList(List<Category> categoryList) {
        if ( categoryList == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<CategoryDTO>( categoryList.size() );
        for ( Category category : categoryList ) {
            list.add( toDTO( category ) );
        }

        return list;
    }

    @Override
    public CategoryCreateDTO toCreateDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryCreateDTO categoryCreateDTO = new CategoryCreateDTO();

        categoryCreateDTO.setName( category.getName() );
        categoryCreateDTO.setCategoryType( category.getCategoryType() );

        return categoryCreateDTO;
    }

    @Override
    public CategoryUpdateDTO toUpdateDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryUpdateDTO categoryUpdateDTO = new CategoryUpdateDTO();

        categoryUpdateDTO.setId( category.getId() );
        categoryUpdateDTO.setName( category.getName() );
        categoryUpdateDTO.setCategoryType( category.getCategoryType() );

        return categoryUpdateDTO;
    }

    @Override
    public Category toUpdateEntity(CategoryUpdateDTO categoryUpdateDTO) {
        if ( categoryUpdateDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryUpdateDTO.getId() );
        category.setName( categoryUpdateDTO.getName() );
        category.setCategoryType( categoryUpdateDTO.getCategoryType() );

        return category;
    }

    @Override
    public Category toCreateEntity(CategoryCreateDTO categoryCreateDTO) {
        if ( categoryCreateDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( categoryCreateDTO.getName() );
        category.setCategoryType( categoryCreateDTO.getCategoryType() );

        return category;
    }
}
