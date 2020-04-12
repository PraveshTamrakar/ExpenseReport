package com.assignment.expense.controller;

import com.assignment.expense.entity.Expense;
import com.assignment.expense.service.IExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class ExpenseControllerTest {

    private ExpenseController expenseControllerUnderTest;

    @BeforeMethod
    public void setUp() {
        expenseControllerUnderTest = new ExpenseController();
        expenseControllerUnderTest.expenseService = mock(IExpenseService.class);
    }

    @Test
    public void testGetExpenseById() {
        // Setup

        // Configure IExpenseService.getExpenseById(...).
        final Expense expense = new Expense();
        expense.setId(0L);
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(0L);
        expense.setCreatedDate(new Date(0L));
        expense.setReporterName("reporterName");
        when(expenseControllerUnderTest.expenseService.getExpenseById(0L)).thenReturn(expense);

        // Run the test
        final ResponseEntity<Expense> result = expenseControllerUnderTest.getExpenseById(0L);

        // Verify the results
    }

    @Test
    public void testGetAllExpenses() {
        // Setup

        // Configure IExpenseService.getAllExpense(...).
        final Expense expense = new Expense();
        expense.setId(0L);
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(0L);
        expense.setCreatedDate(new Date(0L));
        expense.setReporterName("reporterName");
        final List<Expense> expenses = Arrays.asList(expense);
        when(expenseControllerUnderTest.expenseService.getAllExpense()).thenReturn(expenses);

        // Run the test
        final ResponseEntity<List<Expense>> result = expenseControllerUnderTest.getAllExpenses();

        // Verify the results
    }

    @Test
    public void testAddExpense() {
        // Setup
        final Expense expense = new Expense();
        expense.setId(0L);
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(0L);
        expense.setCreatedDate(new Date(0L));
        expense.setReporterName("reporterName");

        // Run the test
        final ResponseEntity<Object> result = expenseControllerUnderTest.addExpense(expense);

        // Verify the results
        verify(expenseControllerUnderTest.expenseService).addExpense(any(Expense.class));
    }

    @Test
    public void testPutExpense() {
        // Setup
        final Expense expense = new Expense();
        expense.setId(0L);
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(0L);
        expense.setCreatedDate(new Date(0L));
        expense.setReporterName("reporterName");

        // Run the test
        final ResponseEntity<Expense> result = expenseControllerUnderTest.putExpense(expense, 0L);

        // Verify the results
        verify(expenseControllerUnderTest.expenseService).updateExpense(any(Expense.class), eq(0L));
    }

    @Test
    public void testDeleteExpenseById() {
        // Setup
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("successfully deleted expense report with id: 0", HttpStatus.OK);

        // Run the test
        final ResponseEntity<Object> result = expenseControllerUnderTest.deleteExpenseById(0L);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(expenseControllerUnderTest.expenseService).deleteExpense(0L);
    }
}
