package com.company.services;

import com.company.models.Expense.*;
import com.company.models.Split.PercentSplit;
import com.company.models.Split.Split;
import com.company.models.User;

import java.util.List;

public class ExpenseService {
    public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits, ExpenseMetaData expenseMetaData){

        switch(expenseType){
            case EQUAL:
                int totalSplits=splits.size();
                double splitAmount= ((double) Math.round(amount*100/totalSplits))/100.0;

                for(Split split:splits){
                    split.setAmount(splitAmount);
                }
                System.out.println(splits.get(0)+"pulkit");
                splits.get(0).setAmount(splitAmount + (amount - splitAmount*totalSplits));
                return new EqualExpense(amount, paidBy, splits, expenseMetaData);
            case EXACT:
                return new ExactExpense(amount,paidBy,splits,expenseMetaData);
            case PERCENT:
                for (Split split : splits) {
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((amount*percentSplit.getPercent())/100.0);
                }
                return new PercentExpense(amount, paidBy, splits, expenseMetaData);
            default:
                return null;
        }
    }
}
