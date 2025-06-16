package ru.zarubin.expensetracker.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.zarubin.expensetracker.dto.TransactionCreateDTO;
import ru.zarubin.expensetracker.dto.TransactionDTO;
import ru.zarubin.expensetracker.dto.TransactionUpdateDTO;
import ru.zarubin.expensetracker.enums.CategoryType;
import ru.zarubin.expensetracker.exception.CategoryNotFoundException;
import ru.zarubin.expensetracker.exception.TransactionNotFoundException;
import ru.zarubin.expensetracker.mapper.TransactionMapper;
import ru.zarubin.expensetracker.model.Category;
import ru.zarubin.expensetracker.model.Transaction;
import ru.zarubin.expensetracker.repository.CategoryRepository;
import ru.zarubin.expensetracker.repository.TransactionRepository;
import ru.zarubin.expensetracker.service.TransactionService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final CategoryRepository categoryRepository;
    @Override
    public List<TransactionDTO> getAll() {
        List<Transaction> transactions = repository.findAll();
        if (transactions.isEmpty()) {
            log.warn("No transactions found in method getAll");
            throw new TransactionNotFoundException("There are no transactions");
        }
        log.info("Found {} transactions in method getAll", transactions.size());
        return mapper.toListDTO(transactions);
    }

    @Override
    public List<TransactionDTO> findByCategoryId(Long id) {
        List<Transaction> transactions = repository.findByCategoryId(id);
        if (transactions.isEmpty()) {
            log.warn("No transactions found in method findByCategoryId");
            throw new TransactionNotFoundException("Transaction with Category id: " + id + " not found");
        }
        log.info("Found {} transactions in method findByCategoryId", transactions.size());
        return mapper.toListDTO(transactions);
    }

    @Override
    public List<TransactionDTO> getByDate(LocalDate date) {
        List<Transaction> transactions = repository.findByDateOfPurchase(date);
        if (transactions.isEmpty()) {
            log.warn("No transactions found in method getByDate ");
            throw new TransactionNotFoundException("Transaction with Date of Purchase: " + date + " not found");
        }
        log.info("Found {} transactions in method getByDate", transactions.size());
        return mapper.toListDTO(transactions);
    }

    @Override
    public List<TransactionDTO> getTransactionByAscendingOrder() {
        List<Transaction> transactions = repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount))
                .toList();
        log.info("Found {} transactions in method getTransactionByAscendingOrder", transactions.size());
        return mapper.toListDTO(transactions);
    }

    @Override
    public List<TransactionDTO> getTransactionByDescendingOrder() {
        List<Transaction> transactions = repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .toList();
        log.info("Found {} transactions in method getTransactionByDescendingOrder", transactions.size());
        return mapper.toListDTO(transactions);
    }

    @Override
    public Double getAllTransactionSum(CategoryType type) {
        List<Transaction> transactions = mapper.toListEntity(findTransactionByCategoryType(type));
        if (transactions.isEmpty()) {
            log.warn("No transactions found in method getAllTransactionSum");
        }
        log.info("Found {} transactions in method getAllTransactionSum", transactions.size());
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();

    }

    @Override
    public Double getTransactionSumByCategory(Long id) {
        List<Transaction> transactions = mapper.toListEntity(findByCategoryId(id));
        if (transactions.isEmpty()) {
            log.warn("No transactions found in method getTransactionSumByCategory");
            throw new TransactionNotFoundException("Transaction with Category id: " + id + " not found");
        } else {
            log.info("Found {} transactions in method getTransactionSumByCategory", transactions.size());
            return transactions.stream()
                    .filter(x -> x.getCategory().getId().equals(id))
                    .mapToDouble(Transaction::getAmount)
                    .sum();
        }
    }
    @Override
    public Double getTransactionSumByDate(LocalDate date) {
        List<Transaction> transactions = mapper.toListEntity(getByDate(date))
                .stream()
                .filter(x -> x.getDateOfPurchase().equals(date))
                .toList();
        if (transactions.isEmpty()) {
            log.warn("No transactions found in method getTransactionSumByDate");
            throw new TransactionNotFoundException("Transaction with Date of Purchase: " + date + " not found");
        } else {
            log.info("Found {} transactions in method getTransactionSumByDate", transactions.size());
            return transactions.stream().mapToDouble(Transaction::getAmount).sum();
        }

    }

    @Override
    public Double getTransactionSumByCategoryAndType(CategoryType type, String name) {
        log.info("Calculating the transaction amount with name:{} and type:{}", name, type);
        List<Transaction> transactions = mapper.toListEntity(findTransactionByName(name))
                .stream()
                .filter(x -> x.getCategory().getCategoryType().equals(type))
                .toList();
        if (transactions.isEmpty()) {
            log.warn("No transactions found in method getTransactionSumByCategoryAndType");
            throw new TransactionNotFoundException("Transaction with name " + name + " and type " + type + " not found");
        } else {
            log.info("Found {} transactions in method getTransactionSumByCategoryAndType", transactions.size());
            return transactions.stream().mapToDouble(Transaction::getAmount).sum();
        }

    }

    @Override
    public TransactionDTO addTransaction(TransactionCreateDTO transactionCreateDTO) {
        log.info("1.transactionCreateDTO {}", transactionCreateDTO);
        Category category = categoryRepository
                .findById(transactionCreateDTO.getCategoryId())
                .orElseThrow(()->new CategoryNotFoundException("Category with id"+transactionCreateDTO.getCategoryId()+" not found"));
        log.info("2.category {}",category);
        Transaction transaction = mapper.toCreateEntity(transactionCreateDTO);
        transaction.setCategory(category);
        log.info("3.transaction {}", transaction);
        repository.save(transaction);
        log.info("Added transaction in method addTransaction");
        return mapper.toDTO(transaction);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting transaction with id {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<TransactionDTO> findTransactionByName(String name) {
        if (repository.findByName(name).isEmpty()) {
            log.warn("No transactions found in method findTransactionByName");
            throw new TransactionNotFoundException("Transaction with name: " + name + " not found");
        }
        log.info("Found {} transactions in method findTransactionByName", repository.findByName(name).size());
        return mapper.toListDTO(repository.findByName(name));
    }

    @Override
    public TransactionDTO updateTransaction(TransactionUpdateDTO transaction) {
        Transaction transactionToUpdate = mapper.toUpdateEntity(transaction);
        Transaction oldTransaction = repository.findById(transactionToUpdate.getId())
                .orElseThrow(() -> {
                            log.warn("Transaction with id {} in method updateTransaction not found", transactionToUpdate.getId());
                            return new TransactionNotFoundException("Transaction: " + transaction + " not found");
                        });
        oldTransaction.setName(transactionToUpdate.getName());
        oldTransaction.setAmount(transactionToUpdate.getAmount());
        oldTransaction.setDateOfPurchase(transactionToUpdate.getDateOfPurchase());
        oldTransaction.setCategory(transactionToUpdate.getCategory());
        log.info("Transaction with saved");
        return mapper.toDTO(repository.save(oldTransaction));

    }


    @Override
    public List<TransactionDTO> findTransactionByCategoryType(CategoryType type) {
        List<Transaction> transactions = repository.findAll()
                .stream()
                .filter(transaction -> transaction.getCategory().getCategoryType().equals(type)).toList();
        if (transactions.isEmpty()) {
            log.warn("No transactions found with type: {}", type);
            throw new TransactionNotFoundException("Transaction with Category : " + type.toString() + " not found");
        }
        log.info("Found {} transactions in method findTransactionByCategoryType", transactions.size());
        return mapper.toListDTO(transactions);
    }
}
