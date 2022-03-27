package com.company;

import javax.sound.midi.SysexMessage;
import java.text.BreakIterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc= new Scanner(System.in);

        while(true){
            String command = sc.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch(commandType){
                case "SHOW":
                    if(commands.length ==1){
                        // print all balances;
                    }else{
                        // print  only user
                      String userId=commands[1];
                    }
                    break;

                case "EXPENSE":
                    String paidBy=commands[1];
                    Double amount =Double.parseDouble(commands[2]);
                    int noOfUser=Integer.parseInt(commands[3]);
                    String expenseType=commands[4+noOfUser];
                    //// create splits;
                    switch (expenseType){
                        case "EQUAL" :
                            // do equal expense;
                            System.out.println("Equal");
                            break;
                        case "EXACT":
                            System.out.println("exact");
                            break;

                        case "PERCENT":
                            System.out.println("percent");
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
