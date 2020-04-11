package com.assignment.expense.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private String message;
    private String details;

    public ExceptionResponse(String message, String details) {
        super();
        this.message = message;
        this.details = details;
    }

}
