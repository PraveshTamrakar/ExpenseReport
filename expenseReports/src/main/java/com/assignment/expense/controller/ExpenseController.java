package com.assignment.expense.controller;

import com.assignment.expense.entity.Expense;
import com.assignment.expense.service.IExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class ExpenseController {

    @Autowired
    IExpenseService expenseService;

    @GetMapping(value = "expense/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Expense> getExpenseById(@PathVariable("id") long id) {

        log.debug("request: getExpenseById " + id);
        Expense expense = expenseService.getExpenseById(id);
        log.debug("response: getExpenseById " + expense.toString());

        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @GetMapping(value = "expense", produces = {"application/json"})
    public ResponseEntity<List<Expense>> getAllExpenses() {

        log.debug("request: getAllExpenseList");
        List<Expense> list = expenseService.getAllExpense();
        log.debug("response: getAllExpense " + list.toString());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "expense", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> addExpense(@RequestBody @Valid Expense expense) {

        log.debug("request to create expense with " + expense.toString());
        expenseService.addExpense(expense);
        log.debug("response returned for create expense with " + expense.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }

    @PutMapping(value = "expense/{id}", produces = {"application/json"})
    public ResponseEntity<Expense> putExpense(@RequestBody @Valid Expense expense, @PathVariable("id") long id) {

        log.debug("request to update expense for id: " + id + "with " + expense.toString());
        expenseService.updateExpense(expense, id);
        log.debug("response returned for update expense with id " + id + ":" + expense.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);

    }

    @DeleteMapping(value = "expense/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> deleteExpenseById(@PathVariable("id") long id) {
        log.debug("request to delete expense with id: " + id);
        expenseService.deleteExpense(id);
        log.debug("successfully deleted expense with id: " + id);

        return ResponseEntity.status(HttpStatus.CREATED).body("successfully deleted expense report with id: " + id);        //return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }


}
