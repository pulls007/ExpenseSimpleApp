package com.company.Controller;

import com.company.models.Expense.Expense;
import com.company.models.Expense.ExpenseMetaData;
import com.company.models.Expense.ExpenseType;
import com.company.models.Split.Split;
import com.company.models.User;
import com.company.repository.ExpenseRepos;
import com.company.services.ExpenseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseController {

    ExpenseRepos expenseRepos;
    ExpenseService expenseService;

    /// inititalising on constructer
    public ExpenseController(ExpenseRepos expenseRepos) {
        this.expenseRepos = expenseRepos;
        this.expenseService=new ExpenseService();
//        expenseService;
    }

    public ExpenseRepos getExpenseRepos() {
        return expenseRepos;
    }

    public ExpenseService getExpenseService() {
        return expenseService;
    }
    public User getUserFromMap(String id){
        return expenseRepos.getUserMap().get(id);
    }

    public void addUser(User user){
        Map<String,User> userMap= expenseRepos.getUserMap();
        if(userMap.containsKey(user.getId())){
            System.out.println("Already present user");
//            throw  new Exception("ALREADY PRESENT");
            return;
        }
       userMap.put(user.getId(),user);
        expenseRepos.getBalanceSheet().put(user.getId(), new HashMap<String, Double>());
        System.out.println("User Created : " + user.toString());
    }
//    public void updateBalanceSheet()
    public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits, ExpenseMetaData expenseMetadata){
        Expense expense=expenseService.createExpense(expenseType,amount,expenseRepos.getUserMap().get(paidBy),splits,expenseMetadata);
        expenseRepos.getExpenses().add(expense);
        // we have creaete an expense now;
        // we have to update the balance sheet now;
        /// updating paidby
        Map<String, Double> balancePaidBY = expenseRepos.getBalanceSheet().get(paidBy);

        for(Split split:splits){
            String paidTo=split.getUser().getId();
            Map<String, Double> balancePaidTo= expenseRepos.getBalanceSheet().get(paidTo);
            if(!balancePaidBY.containsKey(paidTo)){
                balancePaidBY.put(paidTo,0.0);
            }
            // updating paidby balance sheet;
            balancePaidBY.put(paidTo, balancePaidBY.get(paidTo) + split.getAmount());
            ///updating paidto balance sheet;
            if(!balancePaidTo.containsKey(paidBy)){
                balancePaidTo.put(paidBy,0.0);
            }
            balancePaidTo.put(paidBy,balancePaidTo.get(paidBy)-split.getAmount());
//            System.out.println("Paid By " + paidBy+  " paidto " + paidTo);
        }
    }

    public void showBalance(String userId) {
        boolean isEmpty = true;
        for (Map.Entry<String, Double> userBalance : expenseRepos.getBalanceSheet().get(userId).entrySet()) {
            if (userBalance.getValue() != 0) {
                isEmpty = false;
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    public void showBalances(){
        boolean isEmpty=true;
        for(Map.Entry<String,Map<String,Double>> allBalances:expenseRepos.getBalanceSheet().entrySet()){
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }
        if (isEmpty) {
            System.out.println("No balances");
        }
    }
    private void printBalance(String user1, String user2, double amount) {

        String user1Name = expenseRepos.getUserMap().get(user1).getName();
        String user2Name = expenseRepos.getUserMap().get(user2).getName();
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }
}
