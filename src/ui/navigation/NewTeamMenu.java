package ui.navigation;

import domain.team.Player;
import domain.team.Team;
import domain.team.TeamHistory;

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
    public void show(HashMap<String, Menu> navigation) {
        clearScreen();
        super.show(navigation, "b");
        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "b")) {
            case "1" -> createNewTeam(navigation);
            default -> navigation.get("prevMenu").show(navigation);
        }
    }

    private void createNewTeam(HashMap<String, Menu> navigation) {
        clearScreen();
        String teamName = ui.getUserInput("Type the new team name:");
        ArrayList<Player> members = new ArrayList<>();
        boolean willAddPlayer = true;
        while (willAddPlayer) {
            clearScreen();
            boolean isDone = members.size() >= 2;
            String headerMsg = teamName + "\n";
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

        teams.put(teamName, new Team(teamName, members, new TeamHistory()));

        navigation.put("currMenu", navigation.get("Teams"));
        ((TeamMenu) navigation.get("Team")).show(navigation, teams.get(teamName));
    }

    private Player createPlayer() {
        return new Player(ui.getUserInput("Type the name of the player:"));
    }
}
