package com.assignment.expense.service;

import com.assignment.expense.entity.Expense;

import java.util.List;

public interface IExpenseService {

    List<Expense> getAllExpense();

    Expense getExpenseById(long id);

    void addExpense(Expense expense);

    void updateExpense(Expense expense, Long id);

    void deleteExpense(long id);
}
