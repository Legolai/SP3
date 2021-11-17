package ui;

import database.DBConnectorIO;
import database.FileIO;
import database.IO;
import domain.team.Team;
import domain.tournament.Tournament;
import ui.navigation.*;

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
        io = new DBConnectorIO();
        navigation = new Navigator();
    }

    public void start() {
        loadData();
        setupNavigtion();
    }

    public void run() {
        isRunning = true;
        navigation.setCurrentMenu("Start");
        while (isRunning) {
            navigation.goTo("Start");
        }
    }

    public void quit()  {
        ui.println("The Program is quiting...");
        saveData();
        isRunning = false;
    }

    private void setupNavigtion() {
        navigation.addDestination(new StartMenu("Start", true));
        navigation.addDestination(new AdminMenu("Admin", true));
        navigation.addDestination(new TournamentsMenu("Tournaments", true, tournaments));
        navigation.addDestination(new TournamentMenu("Tournament", false));
        navigation.addDestination(new NewTournamentMenu("New Tournament", true ,tournaments));
        navigation.addDestination(new TeamsMenu("Teams", true, teams));
        navigation.addDestination(new TeamMenu("Team", false));
        navigation.addDestination(new NewTeamMenu("New Team", true, teams));
        navigation.addDestination(new QuitMenu("Quit", false,this));
    }

    private void loadData() {
        tournaments = io.loadTournaments();
        teams = io.loadTeams();
    }

    private void saveData() {
        io.saveTournaments(tournaments);
        io.saveTeams(teams);
    }
}
