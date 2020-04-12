package com.assignment.expense.constants;

public class SQLQueryList {


    public static final String SQL_GET_ALL_EXPENSE_QUERY = "SELECT id, amount, expenseReportName, expenseName, createdDate, reporterName FROM expense";
    public static final String SQL_GET_EXPENSE_BY_ID = "SELECT id, amount, expenseReportName, expenseName, createdDate, reporterName FROM expense WHERE id = ?";
    public static final String SQL_INSERT_EXPENSE = "INSERT INTO expense (amount, expenseReportName, expenseName, CreatedDate, reporterName) values (?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_BY_EXPENSE_ID = "UPDATE expense SET amount=?, expenseReportName=?, expenseName=?, reporterName =? WHERE id = ?";
    public static final String SQL_DELETE_BY_EXPENSE_ID = "DELETE FROM expense WHERE id=?";
}
