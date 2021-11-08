package ui;

import domain.team.Team;
import domain.tournament.Tournament;
import database.IO;
import ui.navigation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Program {

    private boolean isRunning;
    private ArrayList<Tournament> tournaments;
    private final HashMap<String, Team> teams;
    private final UI ui;
    private final IO io;
    private final HashMap<String, Menu> navigation;

    public Program() {
        tournaments = new ArrayList<>();
        teams = new HashMap<>();
        ui = new UI();
        io = new IO();
        navigation = new HashMap<>();
    }

    public void start() {
        loadData();
        setupNavigtion();
    }

    public void run() {
        isRunning = true;
        while (isRunning) {
            navigation.get("Home").show(navigation);
        }
    }

    public void quit()  {
        ui.println("The Program is quiting...");
        saveData();
        isRunning = false;
    }

    private void setupNavigtion() {
        navigation.put("Home", new MainMenu("Home", true,tournaments));
        navigation.put("Tournaments", new TournamentsMenu("Tournaments", true, tournaments));
        navigation.put("New Tournament", new NewTournamentMenu("New Tournament", true ,tournaments));
        navigation.put("Teams", new TeamsMenu("Teams", true, teams));
        navigation.put("Team", new TeamMenu("Team", false));
        navigation.put("New Team", new NewTeamMenu("New Team", true, teams));
        navigation.put("Quit", new QuitMenu("Quit", false,this));
        navigation.put("currMenu", null);
        navigation.put("prevMenu", null);
    }

    private void loadData() {
        try {
            tournaments = io.loadTournaments("resources/tournaments.txt");
        } catch (FileNotFoundException e) {
            ui.println(e.getMessage());
        }
    }

    private void saveData() {
        try {
            io.saveTournamentsToFile("resources/text.txt", tournaments);
        } catch (IOException e) {
            ui.println(e.getMessage());
        }
    }
}
