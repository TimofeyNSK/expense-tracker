package ru.zarubin.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zarubin.expensetracker.enums.CategoryType;
import ru.zarubin.expensetracker.model.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
   Optional<Category> findByName(String name);
     List<Category> findByType(CategoryType type);
}
