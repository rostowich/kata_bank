package com.lacombedulionvert.kata_bank;

import java.time.LocalDate;
import java.util.Objects;

public class AccountOperation {

    private LocalDate date;
    private OperationType operationType;
    private int amount;

    public AccountOperation() {
    }

    public AccountOperation(LocalDate date, OperationType operationType, int amount) {
        this.date = date;
        this.operationType = operationType;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountOperation that = (AccountOperation) o;
        return amount == that.amount && Objects.equals(date, that.date) && operationType == that.operationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, operationType, amount);
    }
}