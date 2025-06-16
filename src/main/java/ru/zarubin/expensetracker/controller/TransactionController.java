package ru.zarubin.expensetracker.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zarubin.expensetracker.dto.TransactionCreateDTO;
import ru.zarubin.expensetracker.dto.TransactionDTO;
import ru.zarubin.expensetracker.dto.TransactionUpdateDTO;
import ru.zarubin.expensetracker.enums.CategoryType;
import ru.zarubin.expensetracker.service.TransactionService;

import java.time.LocalDate;

import java.util.List;
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    @GetMapping("/get_all")
    public List<TransactionDTO> getAllTransactions() {
        log.info("Get all transactions");
        return  transactionService.getAll();
    }
    @GetMapping("/by_category")
    public List<TransactionDTO> getTransactionsByCategory(@NotNull @RequestParam  Long id) {
        log.info("Get transactions by category");
        return transactionService.findByCategoryId(id);
    }
    @GetMapping("/by_date")
    public List<TransactionDTO> getTransactionsByDate(@NotNull @RequestParam  LocalDate date) {
        log.info("Get transactions by date {}", date);
        return transactionService.getByDate(date);
    }
    @PostMapping("/add_transaction")
        public TransactionDTO addTransaction(@Valid @RequestBody TransactionCreateDTO transaction) {
        log.info("transactionCreateDTO {}", transaction);
            return transactionService.addTransaction(transaction);

        }
        @GetMapping("/by_ascending_order")
    public List<TransactionDTO> getTransactionByAscendingOrder(){
        log.info("Get transactions by ascending order");
        return transactionService.getTransactionByAscendingOrder();
        }
        @GetMapping("/by_descending_order")
    public List<TransactionDTO> getTransactionByDescendingOrder(){
        log.info("Get transactions by descending order");
        return transactionService.getTransactionByDescendingOrder();
        }
        @GetMapping("/all_sum_transaction_by_category_type")
    public Double getAllTransactionSum(@NotNull @RequestParam CategoryType categoryType){
        log.info("Get transaction sum by category type {}",categoryType);
        return transactionService.getAllTransactionSum(categoryType);
        }
        @GetMapping("/transaction_sum_by_category_id")
    public Double getTransactionSumByCategory(@NotNull @RequestParam  Long id){
        log.info("Get transaction sum by category with id {}", id);
        return transactionService.getTransactionSumByCategory(id);
        }
        @GetMapping("/transaction_sum_by_date")
    public Double getTransactionSumByDate(@NotNull @RequestParam  LocalDate date){
        log.info("Get transaction sum by date {}", date);
        return transactionService.getTransactionSumByDate(date);
        }
        @GetMapping("/transaction_sum_by_category_and_type")
     public Double getTransactionSumByCategoryAndType(@NotNull @RequestParam  CategoryType categoryType, @RequestParam @NotBlank String name){
        log.info("Get transaction sum by category and type {}", categoryType);
        return transactionService.getTransactionSumByCategoryAndType(categoryType, name);
     }
     @DeleteMapping("/delete")
     public ResponseEntity<Void> deleteTransaction(@Valid @RequestBody Long id){
        log.info("Delete transaction {}", id);
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
     }
     @GetMapping("/by_name")
    public List<TransactionDTO> findTransactionByName(@NotBlank @RequestParam  String name){
        log.info("Find transaction by name {}", name);
        return transactionService.findTransactionByName(name);
     }
     @PutMapping("/update")
    public TransactionDTO updateTransaction(@Valid @RequestBody TransactionUpdateDTO transaction){
        log.info("Update transaction {}", transaction);
        return transactionService.updateTransaction(transaction);
     }
     @GetMapping("/by_category_type")
    public List<TransactionDTO> findTransactionByCategoryType(@NotNull @RequestParam  CategoryType categoryType){
        log.info("Find transactions by category type {}", categoryType);
        return transactionService.findTransactionByCategoryType(categoryType);
     }




}
