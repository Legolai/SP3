package ui.navigation;

import domain.team.Team;

import java.util.HashMap;

public class TeamMenu extends Menu{

    Team team;

    public TeamMenu(String name, boolean isHeaderShown) {
        super(name, isHeaderShown, new String[]{
                "View all team members",
                "Edit Team members",
                "View Stats",
                "Go back"
        });
    }

    @Override
    public void show(Navigator navigation) {

    }

    public void show(Navigator navigation, Team team) {
        clearScreen();
        super.showCustomHeader(team.getName());
        super.showMenu( "b");

        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "b")) {
            case "1" -> show(navigation, team);
            case "2" -> show(navigation, team);
            case "3" -> show(navigation, team);
            default -> navigation.goBack();
        }
    }
}
