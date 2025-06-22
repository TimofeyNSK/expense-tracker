package ru.zarubin.expensetracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "transactions",description = "Управление транзакциями")
public class TransactionController {
    private final TransactionService transactionService;
    @GetMapping("/get_all")
    @Operation(summary = "Получить все транзакции",description = "Возвращает список транзакций")
    public List<TransactionDTO> getAllTransactions() {
        log.info("Get all transactions");
        return  transactionService.getAll();
    }
    @Operation(summary = "Получить транзакции по категории",description = "Возвращает список транзакций по категории id")
    @GetMapping("/by_category")
    public List<TransactionDTO> getTransactionsByCategory(@Parameter(name = "id",description = "id не null", required = true)
                                                              @NotNull @RequestParam  Long id) {
        log.info("Get transactions by category");
        return transactionService.findByCategoryId(id);
    }
    @Operation(summary = "Полчить траназкцию по дате",
            description = "Возвращает список транзакций по дате")
    @GetMapping("/by_date")
    public List<TransactionDTO> getTransactionsByDate(@Parameter(name = "date",
            description = "Дата транзакции, не  может быть null.Формат YYYY-MM-DD",
    required = true,
    example = "2025-06-01")
                                                          @NotNull @RequestParam  LocalDate date) {
        log.info("Get transactions by date {}", date);
        return transactionService.getByDate(date);
    }
    @Operation(summary = "Добавить транзакцию",description = "Добавляет транзакцию в список")
    @PostMapping("/add_transaction")
        public TransactionDTO addTransaction(@Parameter(name = "transaction",
            description = "Транзакция со всеми полями кроме id",
            required = true)
                                                 @Valid @RequestBody TransactionCreateDTO transaction) {
        log.info("transactionCreateDTO {}", transaction);
            return transactionService.addTransaction(transaction);

        }
        @Operation(summary = "Получить транзакцию по возрастанию суммы",
                description = "Возвращает список транзакций с сортировкой объектов по возроастанию суммы")
        @GetMapping("/by_ascending_order")
    public List<TransactionDTO> getTransactionByAscendingOrder(){
        log.info("Get transactions by ascending order");
        return transactionService.getTransactionByAscendingOrder();
        }
    @Operation(summary = "Получить транзакцию по убыванию суммы",
            description = "Возвращает список транзакций с сортировкой объектов по убыванию суммы")
        @GetMapping("/by_descending_order")
    public List<TransactionDTO> getTransactionByDescendingOrder(){
        log.info("Get transactions by descending order");
        return transactionService.getTransactionByDescendingOrder();
        }
    @Operation(summary = "Сумма транзакций по типу категории",description = "Возвращает список транзакций с заданным типом категории")
        @GetMapping("/all_sum_transaction_by_category_type")
    public Double getAllTransactionSum(@Parameter(name = "categoryType",
            description = "Тип категории не null.Можеть быть INCOME EXPENSE,INVESTMENT",required = true)
                                           @NotNull @RequestParam CategoryType categoryType){
        log.info("Get transaction sum by category type {}",categoryType);
        return transactionService.getAllTransactionSum(categoryType);
        }
    @Operation(summary = "Сумма транзакции по id категории",description = "Возвращает сумма транзакции по id категории")
        @GetMapping("/transaction_sum_by_category_id")
    public Double getTransactionSumByCategory(@Parameter(name = "id",description = "id не null", required = true)
                                                  @NotNull @RequestParam  Long id){
        log.info("Get transaction sum by category with id {}", id);
        return transactionService.getTransactionSumByCategory(id);
        }
    @Operation(summary = "Получить транзакции по дате",description = "Возвращает список транзакции по дате")
        @GetMapping("/transaction_sum_by_date")
    public Double getTransactionSumByDate(@Parameter(name = "date",description = "Дата не null",required = true)
                                              @NotNull @RequestParam  LocalDate date){
        log.info("Get transaction sum by date {}", date);
        return transactionService.getTransactionSumByDate(date);
        }
    @Operation(summary = "Сумма транзакции по имени транзакции и типу категории",
            description = "Возвращает сумму транзакции по имени транзакции и типу категории")
        @GetMapping("/transaction_sum_by_transaction_name_and_type")
     public Double getTransactionSumByTransactionNameAndType(@Parameter(name = "categoryType",
            description = "Тип категории не null.Можеть быть INCOME EXPENSE,INVESTMENT", required = true)
                                                                 @NotNull @RequestParam  CategoryType categoryType,
                                                             @Parameter(name = "name",
                                                                     description = "Имя не может быть пустым",
                                                                     required = true)
                                                             @RequestParam @NotBlank String name){
        log.info("Get transaction sum by category and type {}", categoryType);
        return transactionService.getTransactionSumByTransactionNameAndType(categoryType, name);
     }
    @Operation(summary = "Удаление траназкции",description = "Удаляет транзакцию по id")
     @DeleteMapping("/delete")
     public ResponseEntity<Void> deleteTransaction(@Parameter(name = "id",description = "id не null",required = true)
                                                       @Valid @RequestBody @NotNull Long id){
        log.info("Delete transaction {}", id);
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
     }
    @Operation(summary = "Поиск по имени",description = "Возвращает список транзакции по имени транзакции")
     @GetMapping("/find_by_name")
    public List<TransactionDTO> findTransactionByName(@Parameter(name = "name",description = "Не может быть пустым",required = true)
                                                          @NotBlank @RequestParam  String name){
        log.info("Find transaction by name {}", name);
        return transactionService.findTransactionByName(name);
     }
    @Operation(summary = "Обновление",description = "Обновляет поля транзакции крмое id")
     @PutMapping("/update")
    public TransactionDTO updateTransaction(@Parameter(name = "transaction",required = true)@Valid @RequestBody TransactionUpdateDTO transaction){
        log.info("Update transaction {}", transaction);
        return transactionService.updateTransaction(transaction);
     }
    @Operation(summary = "Поиск транзакций по типу категории",description = "Возвращает список транзакций по типу категории")
     @GetMapping("/find_transaction_by_category_type")
    public List<TransactionDTO> findTransactionByCategoryType(@Parameter(name = "categoryType",
            description = "Тип категории не null.Можеть быть INCOME EXPENSE,INVESTMENT",required = true)
                                                                  @NotNull @RequestParam  CategoryType categoryType){
        log.info("Find transactions by category type {}", categoryType);
        return transactionService.findTransactionByCategoryType(categoryType);
     }




}
