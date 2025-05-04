package ru.zarubin.expensetracker.exception;

public class CategoryTypeNotFoundException extends RuntimeException{
    public CategoryTypeNotFoundException(String message){
        super(message);
    }
}
