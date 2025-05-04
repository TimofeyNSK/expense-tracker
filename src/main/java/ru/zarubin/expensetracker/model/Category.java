package ru.zarubin.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zarubin.expensetracker.enums.CategoryType;

@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryType type;


}
