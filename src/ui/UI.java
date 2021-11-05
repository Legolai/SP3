package ui;

import java.util.Scanner;
import java.util.Set;

public class UI {
    Scanner sc;
    public String getUserOption(String msg, String[] validOptions) {
        sc = new Scanner(System.in);
        print(msg + " ");
        String s = sc.next();
        if (!Set.of(validOptions).contains(s)){
            println("Unvaild input!");
            s = getUserOption(msg,validOptions);
        }
        return s.toLowerCase();
    }

    public String getUserOption(String msg, int numOfValidOptions, String lastOptionKey) {
        sc = new Scanner(System.in);
        print(msg + " ");
        String s = sc.next();
        String[] validOptions = new String[numOfValidOptions];

        for (int i = 1; i < numOfValidOptions; i++) {
            validOptions[i-1] = i + "";
        }
        validOptions[numOfValidOptions-1] = lastOptionKey;
        if (!Set.of(validOptions).contains(s)){
            println("(Unvaild input!) ");
            s = getUserOption(msg,validOptions);
        }
        return s.toLowerCase();
    }

    public String getUserInput(String msg) {
        sc = new Scanner(System.in);
        print(msg + " ");
        return sc.nextLine();
    }

    public void waitForUser(String msg) {
        sc = new Scanner(System.in);
        print(msg + " ");
        sc.nextLine();
    }

    public void waitForUser() {
        sc = new Scanner(System.in);
        print("Press enter to continue. ");
        sc.nextLine();
    }


    public void printOptions(String[] msgOptions, String lastOptionKey){
        newLine();
        for (int i = 0; i < msgOptions.length; i++) {
            if (i == msgOptions.length-1 && !lastOptionKey.equals("")) {
                println("("+ lastOptionKey.toUpperCase() +")" + " " + msgOptions[i]);
                continue;
            }
            println("("+ (i+1) +")" + " " + msgOptions[i]);
        }
        newLine();
    }

    public void println(String msg){
        System.out.println(msg);
    }

    public void newLine(){
        System.out.println();
    }

    public void print(String msg){
        System.out.print(msg);
    }

    public void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
