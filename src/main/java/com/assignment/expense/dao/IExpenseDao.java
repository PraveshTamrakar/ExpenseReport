package com.assignment.expense.dao;

import com.assignment.expense.entity.Expense;

import java.util.List;

public interface IExpenseDao {

    List<Expense> getAllExpense(); // Method returns all Expense reports from Database

    Expense getExpenseById(long id); // Method returns an expense report, based on given id from database

    void addExpense(Expense expense); // Method to Add an expense into database

    void updateExpense(Expense expense, long id); //Method to update an expense report with given Id into database

    void deleteExpense(long id); // Method to delete an expense report from database
}
