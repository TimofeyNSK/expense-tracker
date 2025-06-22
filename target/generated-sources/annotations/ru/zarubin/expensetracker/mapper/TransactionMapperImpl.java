package ru.zarubin.expensetracker.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.zarubin.expensetracker.dto.CategoryDTO;
import ru.zarubin.expensetracker.dto.TransactionCreateDTO;
import ru.zarubin.expensetracker.dto.TransactionDTO;
import ru.zarubin.expensetracker.dto.TransactionUpdateDTO;
import ru.zarubin.expensetracker.model.Category;
import ru.zarubin.expensetracker.model.Transaction;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-22T16:16:49+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDTO toDTO(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setId( transaction.getId() );
        transactionDTO.setName( transaction.getName() );
        transactionDTO.setAmount( transaction.getAmount() );
        transactionDTO.setDateOfPurchase( transaction.getDateOfPurchase() );
        transactionDTO.setCategory( categoryToCategoryDTO( transaction.getCategory() ) );

        return transactionDTO;
    }

    @Override
    public Transaction toEntity(TransactionDTO transactionDTO) {
        if ( transactionDTO == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setId( transactionDTO.getId() );
        transaction.setName( transactionDTO.getName() );
        transaction.setAmount( transactionDTO.getAmount() );
        transaction.setDateOfPurchase( transactionDTO.getDateOfPurchase() );
        transaction.setCategory( categoryDTOToCategory( transactionDTO.getCategory() ) );

        return transaction;
    }

    @Override
    public List<TransactionDTO> toListDTO(List<Transaction> transaction) {
        if ( transaction == null ) {
            return null;
        }

        List<TransactionDTO> list = new ArrayList<TransactionDTO>( transaction.size() );
        for ( Transaction transaction1 : transaction ) {
            list.add( toDTO( transaction1 ) );
        }

        return list;
    }

    @Override
    public List<Transaction> toListEntity(List<TransactionDTO> transactionDTO) {
        if ( transactionDTO == null ) {
            return null;
        }

        List<Transaction> list = new ArrayList<Transaction>( transactionDTO.size() );
        for ( TransactionDTO transactionDTO1 : transactionDTO ) {
            list.add( toEntity( transactionDTO1 ) );
        }

        return list;
    }

    @Override
    public Transaction toCreateEntity(TransactionCreateDTO transactionCreateDTO) {
        if ( transactionCreateDTO == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setName( transactionCreateDTO.getName() );
        transaction.setAmount( transactionCreateDTO.getAmount() );
        transaction.setDateOfPurchase( transactionCreateDTO.getDateOfPurchase() );

        return transaction;
    }

    @Override
    public Transaction toUpdateEntity(TransactionUpdateDTO transactionUpdateDTO) {
        if ( transactionUpdateDTO == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setId( transactionUpdateDTO.getId() );
        transaction.setName( transactionUpdateDTO.getName() );
        transaction.setAmount( transactionUpdateDTO.getAmount() );
        transaction.setDateOfPurchase( transactionUpdateDTO.getDateOfPurchase() );
        transaction.setCategory( categoryDTOToCategory( transactionUpdateDTO.getCategory() ) );

        return transaction;
    }

    protected CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setCategoryType( category.getCategoryType() );

        return categoryDTO;
    }

    protected Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );
        category.setCategoryType( categoryDTO.getCategoryType() );

        return category;
    }
}
