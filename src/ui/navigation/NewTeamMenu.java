package ui.navigation;

import domain.team.Player;
import domain.team.Team;

import java.util.ArrayList;
import java.util.HashMap;

public class NewTeamMenu extends Menu {

    private final HashMap<String, Team> teams;

    public NewTeamMenu(String name, boolean isHeaderShown, HashMap<String, Team> teams) {
        super(name, isHeaderShown, new String[]{
                "Create new team",
                "Go back"
        });
        this.teams = teams;
    }

    @Override
    public void show(Navigator navigation) {
        clearScreen();
        super.showMenu("b");
        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "b")) {
            case "1" -> createNewTeam(navigation);
            default -> navigation.goBack();
        }
    }

    private void createNewTeam(Navigator navigation) {
        clearScreen();
        String teamName = ui.getUserInput("Type the new team name:");
        ArrayList<Player> members = new ArrayList<>();
        boolean willAddPlayer = true;
        while (willAddPlayer) {
            clearScreen();
            boolean isDone = members.size() >= 2;
            String headerMsg = "---| " + teamName + " |---\n";
            if (!isDone) {
                headerMsg += "Aleast " + (2 - members.size()) + " more members is need to create this team";
            }
            ui.println(headerMsg);
            String[] options = {
                    "Add player",
                    "Done" + (isDone ? "" : " (missing players!)"),
                    "Cancel"
            };
            ui.printOptions(options, "c");
            switch (ui.getUserOption("Select action:", options.length, "c")) {
                case "1" -> members.add(createPlayer());
                case "2" -> willAddPlayer = !isDone;
                default -> show(navigation);
            }
        }

        int newID = 0;
        for (Team tm : teams.values()) {
            if (tm.getID() > newID) {
                newID = tm.getID();
            }
        }

        teams.put(teamName.toLowerCase(), new Team(newID+1,teamName, members));

        navigation.setCurrentMenu("Teams");
        ((TeamMenu) navigation.goManuelTo("Team")).show(navigation, teams.get(teamName.toLowerCase()));
    }

    private Player createPlayer() {
        String playerName = ui.getUserInput("Type the name of the player:");
        int newID = 0;
        for (Team tm : teams.values()) {
            for (Player p : tm.getTeamMembers()) {
                if (p.getID() > newID) {
                    newID = p.getID();
                }
            }
        }
        return new Player(newID + 1, playerName);
    }
}
