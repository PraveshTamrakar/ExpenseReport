package com.assignment.expense.exceptions;


public class MySQLException extends RuntimeException {

    final String tableName;
    final String operationName;
    final Object fieldValue;

    public MySQLException(String tableName, String operationName, Object fieldValue) {
        super(String.format("Unable to perform %s operation on table %s with id %s", operationName, tableName, fieldValue));
        this.tableName = tableName;
        this.operationName = operationName;
        this.fieldValue = fieldValue;
    }
}
