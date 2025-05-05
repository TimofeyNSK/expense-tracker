package ru.zarubin.expensetracker.mapper;

import org.mapstruct.Mapper;
import ru.zarubin.expensetracker.dto.TransactionCreateDTO;
import ru.zarubin.expensetracker.dto.TransactionDTO;
import ru.zarubin.expensetracker.dto.TransactionUpdateDTO;
import ru.zarubin.expensetracker.model.Transaction;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO toDTO(Transaction transaction);

    Transaction toEntity(TransactionDTO transactionDTO);

    List<TransactionDTO> toListDTO(List<Transaction> transaction);

    List<Transaction> toListEntity(List<TransactionDTO> transactionDTO);

    Transaction toCreateEntity(TransactionCreateDTO transactionCreateDTO);

    Transaction toUpdateEntity(TransactionUpdateDTO transactionUpdateDTO);
}
