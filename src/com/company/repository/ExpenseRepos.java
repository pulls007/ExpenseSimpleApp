package com.company.repository;

import com.company.models.Expense.Expense;
import com.company.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseRepos {
   List<Expense> expenses;
    Map<String, User> userMap;
    Map<String, Map<String, Double>> balanceSheet;

    public ExpenseRepos() {
        expenses = new ArrayList<Expense>();
        userMap = new HashMap<String, User>();
        balanceSheet = new HashMap<String, Map<String, Double>>();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    public Map<String, Map<String, Double>> getBalanceSheet() {
        return balanceSheet;
    }

    public void setBalanceSheet(Map<String, Map<String, Double>> balanceSheet) {
        this.balanceSheet = balanceSheet;
    }
}
