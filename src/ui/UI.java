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
            s = getUserOption(msg,validOptions);
        }
        return s;
    }

    public void println(String msg){
        System.out.println(msg);
    }
    public void print(String msg){
        System.out.print(msg);
    }

    public void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
