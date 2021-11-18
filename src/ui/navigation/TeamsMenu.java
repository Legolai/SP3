package ui.navigation;

import domain.team.Team;

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
    public void show(Navigator navigation) {
        clearScreen();
        super.showMenu("b");
        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "b")) {
            case "1" -> showAllTeams(navigation);
            case "2" -> goToTeam(navigation);
            default -> navigation.goBack();
        }
    }

    private void goToTeam(Navigator navigation) {
        String teamName = ui.getUserInput("Type the team's name:").toLowerCase();
        if (teams.containsKey(teamName)) {
            ((TeamMenu) navigation.goManuelTo("Team")).show(navigation, teams.get(teamName));
        } else {
            ui.println("The team " + teamName + " does not exist");
            ui.waitForUser();
            show(navigation);
        }
    }

    private void showAllTeams(Navigator navigation) {
        ui.newLine();
        StringBuilder s = new StringBuilder();
        for (Team tm : teams.values()) {
            s.append(tm.getName()).append(", ");
            if (s.length() > 40) {
                ui.println(s.toString());
                s = new StringBuilder();
            }
        }
        ui.println(s.toString());
        ui.waitForUser();
        show(navigation);
    }
}
