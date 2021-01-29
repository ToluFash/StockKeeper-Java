package com.stockkeeper.Models;


public class Expense {
    private String expenseType;
    private double amount;
    private String description;

    public Expense(String expenseType, double amount, String description) {
        this.expenseType = expenseType;
        this.amount = amount;
        this.description = description;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
