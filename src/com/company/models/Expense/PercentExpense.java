package com.company.models.Expense;

import com.company.models.Split.Split;
import com.company.models.User;

import java.util.List;

public class PercentExpense extends Expense{
    public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData metadata) {
        super(amount, paidBy, splits, metadata);
    }
}
