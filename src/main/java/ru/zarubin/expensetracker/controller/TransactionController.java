package ru.zarubin.expensetracker.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

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
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    @GetMapping("/get_all")
    public List<TransactionDTO> getAllTransactions() {
        return  transactionService.getAll();
    }
    @GetMapping("/by_category")
    public List<TransactionDTO> getTransactionsByCategory(@NotNull @RequestParam  Long id) {
        return transactionService.findByCategoryId(id);
    }
    @GetMapping("/by_date")
    public List<TransactionDTO> getTransactionsByDate(@NotBlank @RequestParam  LocalDate date) {
        return transactionService.getByDate(date);
    }
    @PostMapping("/add_transaction")
        public String addTransaction(@Valid @RequestBody TransactionCreateDTO transaction) {
            transactionService.addTransaction(transaction);
            return "Transaction saved";
        }
        @GetMapping("/by_ascending_order")
    public List<TransactionDTO> getTransactionByAscendingOrder(){
        return transactionService.getTransactionByAscendingOrder();
        }
        @GetMapping("/by_descending_order")
    public List<TransactionDTO> getTransactionByDescendingOrder(){
        return transactionService.getTransactionByDescendingOrder();
        }
        @GetMapping("/all_sum_transaction_by_category_type")
    public Double getAllTransactionSum(@NotBlank @RequestParam CategoryType type){
        return transactionService.getAllTransactionSum(type);
        }
        @GetMapping("/transaction_sum_by_category")
    public Double getTransactionSumByCategory(@NotBlank @RequestParam  Long id){
        return transactionService.getTransactionSumByCategory(id);//todo Изменить Postman запрос с String на id
        }
        @GetMapping("/transaction_sum_by_date")
    public Double getTransactionSumByDate(@NotBlank @RequestParam  LocalDate date){
        return transactionService.getTransactionSumByDate(date);
        }
        @GetMapping("/transaction_sum_by_category_and_type")
     public Double getTransactionSumByCategoryAndType(@NotBlank @RequestParam  CategoryType type, @RequestParam  String name){
        return transactionService.getTransactionSumByCategoryAndType(type, name);
     }
     @DeleteMapping("/delete")
     public ResponseEntity<Void> deleteTransaction(@Valid @RequestBody Long id){
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
     }
     @GetMapping("/by_name")
    public List<TransactionDTO> findTransactionByName(@NotBlank @RequestParam  String name){
        return transactionService.findTransactionByName(name);
     }
     @PutMapping("/update")
    public TransactionDTO updateTransaction(@Valid @RequestBody TransactionUpdateDTO transaction){
        return transactionService.updateTransaction(transaction);
     }
     @GetMapping("/by_category_type")
    public List<TransactionDTO> findTransactionByCategoryType(@RequestParam  CategoryType type){
        return transactionService.findTransactionByCategoryType(type);
     }




}
