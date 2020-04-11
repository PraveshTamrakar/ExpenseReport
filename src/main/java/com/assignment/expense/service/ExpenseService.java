package com.assignment.expense.service;

import com.assignment.expense.dao.IExpenseDao;
import com.assignment.expense.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService implements IExpenseService {

    @Autowired
    IExpenseDao expenseDao;

    @Override
    public List<Expense> getAllExpense() {
        return expenseDao.getAllExpense();
    }

    @Override
    public Expense getExpenseById(long id) {
        return expenseDao.getExpenseById(id);
    }

    @Override
    public void addExpense(Expense expense) {
        expenseDao.addExpense(expense);
    }

    @Override
    public void updateExpense(Expense expense, Long id) {
        expense.setId(id);
        expenseDao.updateExpense(expense, id);
    }

    @Override
    public void deleteExpense(long id) {
        expenseDao.deleteExpense(id);
    }
}
