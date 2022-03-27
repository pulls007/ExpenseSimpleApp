package com.company.models.Expense;

import com.company.models.Split.PercentSplit;
import com.company.models.Split.Split;
import com.company.models.User;

import java.util.List;

public class PercentExpense extends Expense{
    public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData metadata) {
        super(amount, paidBy, splits, metadata);
    }
    @Override
    public boolean validate(){
        for(Split split:getSplits()){
            if(!(split instanceof PercentSplit)){
                return  false;
            }
        }
        double totalPercent=100;
        double sumSplitPercent=0;
        for(Split split:getSplits()){
            PercentSplit ps=(PercentSplit)split ;
            sumSplitPercent += ps.getPercent();
        }
        if(totalPercent!=sumSplitPercent){
            return  false;
        }

        return true;

    }
}
