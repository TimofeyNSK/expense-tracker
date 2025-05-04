package ru.zarubin.expensetracker.mapper;

import org.mapstruct.Mapper;
import ru.zarubin.expensetracker.dto.TransactionCreateDTO;
import ru.zarubin.expensetracker.dto.TransactionDTO;
import ru.zarubin.expensetracker.dto.TransactionUpdateDTO;
import ru.zarubin.expensetracker.model.Transaction;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    public TransactionDTO toDTO(Transaction transaction);

    public Transaction toEntity(TransactionDTO transactionDTO);
    public List<TransactionDTO> toListDTO(List<Transaction> transaction);
    public List<Transaction> toListEntity(List<TransactionDTO> transactionDTO);
    public Transaction toCreateEntity(TransactionCreateDTO transactionCreateDTO);
    public Transaction toUpdateEntity(TransactionUpdateDTO transactionUpdateDTO);
}
