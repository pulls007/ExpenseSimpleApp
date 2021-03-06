package com.company.models.Expense;

import com.company.models.Split.Split;
import com.company.models.User;

import java.util.List;
import java.util.UUID;

public  abstract class Expense {

    private String id;
    private double amount;
    private User paidBy;
    private List<Split> splits;
    private ExpenseMetaData metadata;

    @Override
    public String toString() {
        return "Expense{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", paidBy=" + paidBy +
                ", splits=" + splits +
                ", metadata=" + metadata +
                '}';
    }

    public Expense(double amount, User paidBy, List<Split> splits, ExpenseMetaData metadata) {
//        this.id = id;
        UUID uuid = UUID.randomUUID();
        this.id=    uuid.toString();
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.metadata = metadata;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public void setMetadata(ExpenseMetaData metadata) {
        this.metadata = metadata;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public ExpenseMetaData getMetadata() {
        return metadata;
    }
    public  abstract boolean validate();
}
