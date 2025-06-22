package ru.zarubin.expensetracker.service;

import ru.zarubin.expensetracker.dto.TransactionCreateDTO;
import ru.zarubin.expensetracker.dto.TransactionDTO;
import ru.zarubin.expensetracker.dto.TransactionUpdateDTO;
import ru.zarubin.expensetracker.enums.CategoryType;
import java.time.LocalDate;
import java.util.List;


public interface TransactionService {
    List<TransactionDTO> getAll();

    List<TransactionDTO> findByCategoryId(Long id);

    List<TransactionDTO> getByDate(LocalDate date);

    List<TransactionDTO> getTransactionByAscendingOrder();

    List<TransactionDTO> getTransactionByDescendingOrder();

    Double getAllTransactionSum(CategoryType type);

    Double getTransactionSumByCategory(Long id);

    Double getTransactionSumByDate(LocalDate date);

    Double getTransactionSumByTransactionNameAndType(CategoryType type, String name);

    TransactionDTO addTransaction(TransactionCreateDTO transaction);

    void deleteById(Long id);

    List<TransactionDTO> findTransactionByName(String name);

    TransactionDTO updateTransaction(TransactionUpdateDTO transaction);

    List<TransactionDTO> findTransactionByCategoryType(CategoryType type);
}
