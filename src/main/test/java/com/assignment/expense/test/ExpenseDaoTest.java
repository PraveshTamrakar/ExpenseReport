package com.assignment.expense.test;

import com.assignment.expense.entity.Expense;
import com.assignment.expense.exceptions.ResourceNotFoundException;
import com.assignment.expense.service.IExpenseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ExpenseDaoTest {

    @Autowired
    IExpenseService expenseService;

    @Test // To check Add and get expense
    @Transactional
    @Rollback(true)
    public void testAddAndGetExpense() {
        Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");
        expenseService.addExpense(expense);

        Expense expense1 = expenseService.getExpenseById(expense.getId());
        Assert.assertEquals(expense.getAmount(), expense1.getAmount());
    }

    @Test // To check add, update and get expense
    @Transactional
    @Rollback(true)
    public void testAddAndUpdateExpense() {
        Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");
        expenseService.addExpense(expense);

        expense.setAmount(500L);
        expenseService.updateExpense(expense, expense.getId());
        Expense expense1 = expenseService.getExpenseById(expense.getId());
        Assert.assertEquals(expense.getAmount(), expense1.getAmount());
    }

    @Test // Add an expense and check if its in bottom of the list
    @Transactional
    @Rollback(true)
    public void testAddExpenseAndCheckInList() {
        final Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");
        expenseService.addExpense(expense);

        List<Expense> expenseList = expenseService.getAllExpense();
        Assert.assertEquals(expense.getAmount(), expenseList.get(expenseList.size() - 1).getAmount());
    }

    @Test // Add and delete an expense
    @Transactional
    @Rollback(true)
    public void testAddAndDeleteExpense() {
        Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");
        expenseService.addExpense(expense);
        expenseService.deleteExpense(expense.getId());
    }

    @Test(expected = ResourceNotFoundException.class) // Get by an invalid Expense Id
    public void testGetInvalidExpenseId() {
        expenseService.getExpenseById(Long.MAX_VALUE);
    }

    @Test(expected = ResourceNotFoundException.class) // Update using an invalid expense Id
    public void testUpdateInvalidExpenseId() {
        final Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");
        expenseService.updateExpense(expense, Long.MAX_VALUE);
    }

    @Test(expected = ResourceNotFoundException.class) // delete an invalid expense Id
    public void testDeleteInvalidExpenseId() {
        final Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");
        expenseService.deleteExpense(Long.MAX_VALUE);
    }

    @Test(expected = ResourceNotFoundException.class) // Add, delete and get deleted expense Id
    @Transactional
    @Rollback(true)
    public void testAddAndDeleteAndGetExpense() {
        Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");
        expenseService.addExpense(expense);
        expenseService.deleteExpense(expense.getId());
        expenseService.getExpenseById(expense.getId());
    }

    @Test(expected = ResourceNotFoundException.class) // Test add, update and delete and get by expense Id
    @Transactional
    @Rollback(true)
    public void testAddAndUpdateAndDeleteAndGetExpense() {
        Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");
        expenseService.addExpense(expense);
        expense.setAmount(1000L);
        expenseService.updateExpense(expense, expense.getId());
        expenseService.deleteExpense(expense.getId());
        expenseService.getExpenseById(expense.getId());
    }


}
