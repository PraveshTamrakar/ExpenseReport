package com.assignment.expense.dao;

import com.assignment.expense.constants.SQLQueryList;
import com.assignment.expense.entity.Expense;
import com.assignment.expense.exceptions.MySQLException;
import com.assignment.expense.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Repository
@Slf4j
public class ExpenseDaoImpl implements IExpenseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Expense> getAllExpense() {
        String sql = SQLQueryList.SQL_GET_ALL_EXPENSE_QUERY;
        RowMapper<Expense> rowMapper = new BeanPropertyRowMapper<>(Expense.class);
        return jdbcTemplate.query(sql, rowMapper); // Do a query on database and return all available expense data

    }

    @Override
    public Expense getExpenseById(long id) {

        String sql = SQLQueryList.SQL_GET_EXPENSE_BY_ID;
        RowMapper<Expense> rowMapper = new BeanPropertyRowMapper<>(Expense.class);
        Expense expense;
        try {
            expense = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (EmptyResultDataAccessException e) { // Throw an exception if there is No data available with given id.
            log.error("Unable to find any Expense Report with id: " + id, e.getMessage());
            throw new ResourceNotFoundException("Expense Report", "id", id);
        }

        return expense;
    }

    @Override
    @Transactional
    public void addExpense(Expense expense) {

        expense.setCreatedDate(Date.valueOf(LocalDate.now()));
        String sql = SQLQueryList.SQL_INSERT_EXPENSE;
        KeyHolder keyHolder = new GeneratedKeyHolder(); // Keyholder is being used here to get last inserted ID, which is finally being sent on response of addExpense
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, expense.getAmount());
            ps.setString(2, expense.getExpenseReportName());
            ps.setString(3, expense.getExpenseName());
            ps.setDate(4, expense.getCreatedDate());
            ps.setString(5, expense.getExpenseReportName());
            return ps;
        }, keyHolder);
        expense.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    @Transactional
    public void updateExpense(Expense expense, long id) {
        String sql = SQLQueryList.SQL_UPDATE_BY_EXPENSE_ID;
        try {
            int numberOfUpdate = jdbcTemplate.update(sql, expense.getAmount(), expense.getExpenseReportName(), expense.getExpenseName(), expense.getExpenseReportName(), id);
            if (numberOfUpdate == 0) { // Check is to confirm if there is any expense report present with given id, if not throw an exception
                log.error("Unable to find any Expense Report with while updating with id: " + id);
                throw new ResourceNotFoundException("Expense Report", "id", id);
            }
        } catch (DataAccessException e) {
            log.error(" Unable to perform update operation for id: " + id);
            throw new MySQLException("update", "notes", id);
        }
    }

    @Override
    @Transactional
    public void deleteExpense(long id) {
        String sql = SQLQueryList.SQL_DELETE_BY_EXPENSE_ID;
        try {
            int numberOfUpdate = jdbcTemplate.update(sql, id);
            if (numberOfUpdate == 0) { // Check is to confirm if there is any expense report present with given id, if not throw an exception
                log.error("Unable to find any Expense Report with while updating with id: " + id);
                throw new ResourceNotFoundException("Expense Report", "id", id);
            }
        } catch (DataAccessException e) {
            log.error(" Unable to perform delete operation for id: " + id);
            throw new MySQLException("Delete", "notes", id);
        }

    }
}
