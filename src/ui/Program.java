package ui;

import domain.team.Team;
import domain.tournament.*;
import ui.menus.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {

    private boolean isRunning;
    private ArrayList<Tournament> tournaments;
    private ArrayList<Team> newTeams;
    private UI ui;
    private HashMap<String, Menu> menus;

    public Program(){
        tournaments =  new ArrayList<>();
        newTeams = new ArrayList<>();
        ui = new UI();
        menus = new HashMap<>();
    }

    public void start() {
        menus.put("Home", new MainMenu("Home",tournaments));
        menus.put("Tournaments", new TournamentsMenu("Tournaments"));
        menus.put("New Tournament",  new NewTournamentMenu("New Tournament"));
        menus.put("Quit", new QuitMenu("Quit",this));
        menus.put("currMenu",  menus.get("Home"));
        menus.put("prevMenu",  menus.get("Home"));
    }

    public void run() {
        isRunning = true;
        while(isRunning){
            menus.get("Home").show(menus);
        }
    }

    public void quit() {
        ui.println("The Program is quiting...");
        isRunning = false;
    }


}
