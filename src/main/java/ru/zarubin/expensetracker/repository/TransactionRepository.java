package ru.zarubin.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zarubin.expensetracker.model.Transaction;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
     List<Transaction> findByDateOfPurchase(LocalDate date);
    List<Transaction> findByCategoryId(Long id);
    List<Transaction> findByName(String name);


}
