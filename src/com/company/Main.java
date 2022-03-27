package com.company;

import com.company.Controller.ExpenseController;
import com.company.models.Expense.ExpenseType;
import com.company.models.Split.EqualSplit;
import com.company.models.Split.ExactSplit;
import com.company.models.Split.PercentSplit;
import com.company.models.Split.Split;
import com.company.models.User;
import com.company.repository.ExpenseRepos;

import javax.sound.midi.SysexMessage;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc= new Scanner(System.in);
        ExpenseRepos expenseRepos=new ExpenseRepos();
        ExpenseController expenseController=new ExpenseController(expenseRepos);

        // Adding users //
        expenseController.addUser(new User("u1","user1","user@xyz.com","9898989898"));
        expenseController.addUser(new User("u2","user2","user1@xyz.com","9898999999"));
        expenseController.addUser(new User("u3","user3","user2@xyz.com","9898999999"));
        expenseController.addUser(new User("u4","user4","user3@xyz.com","9898999999"));

        while(true){
            String command = sc.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch(commandType){
                case "SHOW":
                    if(commands.length ==1){
                        // print all balances;
                        expenseController.showBalances();
                    }else{
                        // print  only user
                        expenseController.showBalance(commands[1]);

                    }
                    break;

                case "EXPENSE":
                    String paidBy=commands[1];
                    Double amount =Double.parseDouble(commands[2]);
                    int noOfUser=Integer.parseInt(commands[3]);
                    String expenseType=commands[4+noOfUser];
                    List<Split> splits = new ArrayList<>();
                    //// create splits;
                    // ExpenseType expenseType, double amount, String paidBy, List<Split> splits, ExpenseMetaData expenseMetadata
                    switch (expenseType){
                        case "EQUAL" :
                            // do equal expense;
//                            expenseController.create
                            for (int i = 0; i < noOfUser; i++) {
                                User user=expenseController.getUserFromMap(commands[4+i]);
                                splits.add(new EqualSplit(user));
                            }
                            expenseController.addExpense(ExpenseType.EQUAL,amount,paidBy,splits,null);
//                            System.out.println("Equal Expense");
                            break;
                        case "EXACT":
                            for (int i = 0; i < noOfUser; i++) {
                                User user=expenseController.getUserFromMap(commands[4+i]);
                                splits.add(new ExactSplit(user,Double.parseDouble(commands[5 + noOfUser + i])));
                            }
                            expenseController.addExpense(ExpenseType.EXACT,amount,paidBy,splits,null);
//                            System.out.println("exact");
                            break;

                        case "PERCENT":
                            for (int i = 0; i < noOfUser; i++) {
                                User user=expenseController.getUserFromMap(commands[4+i]);
                                splits.add(new PercentSplit(user, Double.parseDouble(commands[5 + noOfUser + i])));
                            }
                            expenseController.addExpense(ExpenseType.PERCENT,amount,paidBy,splits,null);
//                            System.out.println("percent");
                            break;
                    }
                    break;
                case "EXIT":
                    return ;
                default:
                    System.out.println("Invalid input,");
                    break;
            }
        }
//        String commands[]=
    }
}
