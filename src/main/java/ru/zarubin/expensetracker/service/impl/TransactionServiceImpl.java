package ru.zarubin.expensetracker.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zarubin.expensetracker.dto.TransactionCreateDTO;
import ru.zarubin.expensetracker.dto.TransactionDTO;
import ru.zarubin.expensetracker.dto.TransactionUpdateDTO;
import ru.zarubin.expensetracker.enums.CategoryType;
import ru.zarubin.expensetracker.exception.TransactionNotFoundException;
import ru.zarubin.expensetracker.mapper.TransactionMapper;
import ru.zarubin.expensetracker.model.Transaction;
import ru.zarubin.expensetracker.repository.TransactionRepository;
import ru.zarubin.expensetracker.service.TransactionService;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    @Override
    public List<TransactionDTO> getAll() {
        List<Transaction> transactions = repository.findAll();
        if(transactions.isEmpty()){
            throw new TransactionNotFoundException("There are no transactions");
        }
        return mapper.toListDTO(transactions);
    }

    @Override
    public List<TransactionDTO> findByCategoryId(Long id) {
        List<Transaction> transactions = repository.findByCategoryId(id);
        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("Transaction with Category id: " + id + " not found");
        }
        return mapper.toListDTO(transactions);
    }

    @Override
    public List<TransactionDTO> getByDate(LocalDate date) {
        List<Transaction> transactions = repository.findByDateOfPurchase(date);
        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("Transaction with Date of Purchase: " + date + " not found");
        }
        return mapper.toListDTO(transactions);
    }

    @Override
    public List<TransactionDTO> getTransactionByAscendingOrder() {
        List<Transaction> transactions = repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount))
                .toList();
        return mapper.toListDTO(transactions);
    }

    @Override
    public List<TransactionDTO> getTransactionByDescendingOrder() {
        List<Transaction> transactions = repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .toList();
        return mapper.toListDTO(transactions);
    }

    @Override
    public Double getAllTransactionSum(CategoryType type) {
        return mapper.toListEntity(findTransactionByCategoryType(type)).stream().mapToDouble(Transaction::getAmount).sum();

    }

    @Override
    public Double getTransactionSumByCategory(Long id) {
        return mapper.toListEntity(findByCategoryId(id)).stream()
                .filter(x -> x.getCategory().getId().equals(id))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public Double getTransactionSumByDate(LocalDate date) {
       return mapper.toListEntity(getByDate(date)).stream()
                .filter(x -> x.getDateOfPurchase().equals(date))
                .mapToDouble(Transaction::getAmount)
                .sum();

    }

    @Override
    public Double getTransactionSumByCategoryAndType(CategoryType type, String name) {
        mapper.toListEntity(findTransactionByCategoryType(type));
        return mapper.toListEntity(findTransactionByName(name)).stream()
                .filter(x -> x.getCategory().getType().equals(type))
                .mapToDouble(Transaction::getAmount)
                .sum();//todo Оптимизировать
    }

    @Override
    public TransactionDTO addTransaction(TransactionCreateDTO transactionCreateDTO) {
        Transaction transaction = mapper.toCreateEntity(transactionCreateDTO);
         repository.save(transaction);
         return mapper.toDTO(transaction);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<TransactionDTO> findTransactionByName(String name) {
        if(repository.findByName(name).isEmpty()) {
            throw new TransactionNotFoundException("Transaction with name: " + name + " not found");
        }
        return mapper.toListDTO(repository.findByName(name));
    }

    @Override
    public TransactionDTO updateTransaction(TransactionUpdateDTO transaction) {
        Transaction transactionToUpdate = mapper.toUpdateEntity(transaction);
        Transaction oldTransaction = repository.findById(transactionToUpdate.getId())
                .orElseThrow(() -> new TransactionNotFoundException("Transaction: " + transaction + " not found"));
        oldTransaction.setName(transactionToUpdate.getName());
        oldTransaction.setAmount(transactionToUpdate.getAmount());
        oldTransaction.setDateOfPurchase(transactionToUpdate.getDateOfPurchase());
        oldTransaction.setCategory(transactionToUpdate.getCategory());
        repository.save(oldTransaction);
        return mapper.toDTO(repository.save(oldTransaction));

    }
    @Override
    public List<TransactionDTO> findTransactionByCategoryType(CategoryType type) {
        List<Transaction> transactions = repository.findAll()
                .stream()
                .filter(transaction -> transaction.getCategory().getType().equals(type)).toList();
        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("Transaction with Category : " + type.toString() + " not found");
        }
        return mapper.toListDTO(transactions);
    }
}
