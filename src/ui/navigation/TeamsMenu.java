package ui.navigation;

import domain.team.Team;
import domain.tournament.Tournament;

import java.util.HashMap;

public class TeamsMenu extends Menu {

    HashMap<String, Team> teams;

    public TeamsMenu(String name, boolean isHeaderShown, HashMap<String, Team> teams) {
        super(name, isHeaderShown, new String[]{
                "View all teams",
                "Select team",
                "Go back"
        });
        this.teams = teams;
    }

    @Override
    public void show(HashMap<String, Menu> navigation) {
        clearScreen();
        super.show(navigation, "b");
        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "b")) {
            case "1" -> showAllTeams(navigation);
            case "2" -> goToTeam(navigation);
            default -> navigation.get("prevMenu").show(navigation);
        }
    }

    private void goToTeam(HashMap<String, Menu> navigation) {
        String teamName = ui.getUserInput("Type team name: ");
        if (teams.containsKey(teamName)) {
            ((TeamMenu) navigation.get("Team")).show(navigation, teams.get(teamName));
        } else {
            ui.println("The team " + teamName + " does not exist");
            show(navigation);
        }
    }

    private void showAllTeams(HashMap<String, Menu> navigation) {
        for (String key: teams.keySet()) {
            ui.println(teams.get(key).getName());
        }
        ui.waitForUser("Press enter to contiune.");
        show(navigation);
    }
}
