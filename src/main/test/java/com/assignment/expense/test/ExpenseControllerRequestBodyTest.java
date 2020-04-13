package com.assignment.expense.test;

import com.assignment.expense.controller.ExpenseController;
import com.assignment.expense.entity.Expense;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Random;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExpenseControllerRequestBodyTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {

        this.mockMvc = MockMvcBuilders.standaloneSetup(new ExpenseController()).build();
    }

    @Test //Expense Id is not expected from client
    public void testNoId() throws Exception {

        final Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setId(12L);
        expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/expense").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(expense))).andExpect(status().is4xxClientError());

    }

    @Test //Amount shouldn't be negative
    public void testNegativeAmount() throws Exception {

        final Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(-23L);
        expense.setReporterName("reporterName");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/expense").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(expense))).andExpect(status().is4xxClientError());

    }

    @Test //Amount should not be 0
    public void testZeroAmount() throws Exception {

        final Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(0L);
        expense.setReporterName("reporterName");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/expense").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(expense))).andExpect(status().is4xxClientError());

    }

    @Test //ExpenseName and Reporter Name should not be empty
    public void testEmptyAmountName() throws Exception {

        final Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        //expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/expense").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(expense))).andExpect(status().is4xxClientError());

    }

    @Test //Report Name shouldn't be empty
    public void testEmptyReportName() throws Exception {

        final Expense expense = new Expense();
        // expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/expense").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(expense))).andExpect(status().is4xxClientError());

    }

    @Test //Expense Name should not be empty
    public void testEmptyExpenseName() throws Exception {

        final Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        // expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/expense").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(expense))).andExpect(status().is4xxClientError());

    }

    @Test // ReporterName should not be empty
    public void testEmptyReporterName() throws Exception {

        final Expense expense = new Expense();
        expense.setExpenseReportName("expenseReportName");
        expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        //   expense.setReporterName("reporterName");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/expense").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(expense))).andExpect(status().is4xxClientError());

    }

    @Test //ExpenseName and Reporter Name should not be empty
    public void testEmptyExpenseNameAndEmptyReporterName() throws Exception {

        final Expense expense = new Expense();
        //   expense.setExpenseReportName("expenseReportName");
        //  expense.setExpenseName("expenseName");
        expense.setAmount(new Random().nextLong());
        expense.setReporterName("reporterName");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/expense").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(expense))).andExpect(status().is4xxClientError());

    }

    @Test //ExpenseName and Reporter Name should not be empty, along with that amount should be positive
    public void testEmptyExpenseNameAndEmptyReporterNameAndNegativeAmountName() throws Exception {

        final Expense expense = new Expense();
        //   expense.setExpenseReportName("expenseReportName");
        //  expense.setExpenseName("expenseName");
        expense.setAmount(-new Random().nextLong());
        expense.setReporterName("reporterName");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/expense").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(expense))).andExpect(status().is4xxClientError());

    }


    @Test //Expense Node shouldn't be empty
    public void testEmptyNode() throws Exception {

        final Expense expense = new Expense();
        //   expense.setExpenseReportName("expenseReportName");
        //  expense.setExpenseName("expenseName");
        //  expense.setAmount(new Random().nextLong());
        // expense.setReporterName("reporterName");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/expense").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(expense))).andExpect(status().is4xxClientError());

    }

    @Test //Expense Node shouldn't be empty
    public void testNull() throws Exception {

        final Expense expense = null;
        //   expense.setExpenseReportName("expenseReportName");
        //  expense.setExpenseName("expenseName");
        //  expense.setAmount(new Random().nextLong());
        // expense.setReporterName("reporterName");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/expense").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(expense))).andExpect(status().is4xxClientError());

    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
