package com.company.models.Expense;

import com.company.models.Expense.Expense;
import com.company.models.Split.Split;
import com.company.models.User;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata) {
        super(amount, paidBy, splits, metadata);
    }

}
