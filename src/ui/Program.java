package ui;

import java.util.ArrayList;

public class Program {
    private ArrayList<String> tournaments;
    private ArrayList<String> newTeams;
    private boolean isRunning;
    private UI ui;

    public Program(){
         tournaments =  new ArrayList<>();
         newTeams = new ArrayList<>();
         ui = new UI();
    }

    public void start() {

    }

    public void run() {
        isRunning = true;
        while(isRunning){
           mainMenu();
        }

    }

    private void mainMenu(){
        ui.clear();
        String[] options = {"1","2","3","4"};
        ui.println("(1) " + "View tournaments" + (tournaments.isEmpty() ? "(not available)" : ""));
        ui.println("(2) " + "Create tournament");
        ui.println("(3) " + "");
        ui.println("(4) " + "");
        switch (ui.getUserOption("Input:" , options)){
            case "1": {
                if(!tournaments.isEmpty()) {
                    mainMenu();
                } else {
                    tournamentsMenu();
                }
                break;
            }
            case "2": break;
            case "3": break;
            case "4": quit(); break;
        }
    }

    private void tournamentsMenu(){
        ui.clear();
        String[] options = {"1","2","3"};
        ui.println("(1) " + "View all tournaments");
        ui.println("(2) " + "Select tournament");
        ui.println("(3) " +"Go back");
        switch (ui.getUserOption("Input:" , options)){
            case "1": break;
            case "2": break;
            case "3": mainMenu(); break;
        }
    }

    private void quit() {
        ui.println("is quiting...");
        isRunning = false;
    }


}
