package com.assignment.expense.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Date;

@Getter
@Setter
@ToString
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "{expense.ID.NULL}")
    private Long id;

    @NotNull(message = "{expense.reportName.notNULL}")
    @Valid
    private String expenseReportName;

    @NotNull(message = "{expense.name.notNULL}")
    private String expenseName;

    @NotNull(message = "{expense.amount.notNULL}")
    @Min(value = 1, message = "{expense.amount.positive}")
    private Long amount;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date CreatedDate;

    @NotNull(message = "{expense.reporterName.notNULL}")
    @Valid
    private String reporterName;

}
