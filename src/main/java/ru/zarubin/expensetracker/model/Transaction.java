package ru.zarubin.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String name;
    private Double amount;
    private LocalDate dateOfPurchase;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
