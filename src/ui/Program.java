package ui;

import domain.team.Team;
import domain.tournament.Tournament;
import database.IO;
import ui.navigation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class Program {

    private boolean isRunning;
    private HashMap<String, Tournament> tournaments;
    private HashMap<String, Team> teams;
    private final UI ui;
    private final IO io;
    private Navigator navigation;

    public Program() {
        tournaments = new HashMap<>();
        teams = new HashMap<>();
        ui = new UI();
        io = new IO();
        navigation = new Navigator();
    }

    public void start() {
        loadData();
        setupNavigtion();
    }

    public void run() {
        isRunning = true;
        navigation.setCurrentMenu("Home");
        while (isRunning) {
            navigation.goTo("Home");
        }
    }

    public void quit()  {
        ui.println("The Program is quiting...");
        saveData();
        isRunning = false;
    }

    private void setupNavigtion() {
        navigation.addDestination(new MainMenu("Home", true));
        navigation.addDestination(new TournamentsMenu("Tournaments", true, tournaments));
        navigation.addDestination(new TournamentMenu("Tournament", false));
        navigation.addDestination(new NewTournamentMenu("New Tournament", true ,tournaments));
        navigation.addDestination(new TeamsMenu("Teams", true, teams));
        navigation.addDestination(new TeamMenu("Team", false));
        navigation.addDestination(new NewTeamMenu("New Team", true, teams));
        navigation.addDestination(new QuitMenu("Quit", false,this));
    }

    private void loadData() {
        try {
            tournaments = io.loadTournaments("src/resources/tournaments.txt");
        } catch (FileNotFoundException e) {
            ui.println(e.getMessage());
        }
    }

    private void saveData() {
        try {
            io.saveTournamentsToFile("src/resources/tournaments.txt", tournaments);
        } catch (IOException e) {
            ui.println(e.getMessage());
        }
    }
}
