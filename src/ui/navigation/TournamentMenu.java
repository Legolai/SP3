package ui.navigation;

import domain.team.Team;
import domain.tournament.Tournament;

import java.util.HashMap;

public class TournamentMenu extends Menu {
    Tournament tournament;

    public TournamentMenu(String name, boolean isHeaderShown) {
        super(name, isHeaderShown, new String[]{
                "View team ranking",
                "View all contenders",
                "View match program",
                "Go back"
        });
    }

    @Override
    public void show(HashMap<String, Menu> navigation) {

    }

    public void show(HashMap<String, Menu> navigation, Tournament tournament) {
        clearScreen();
        super.showCustomHeader(tournament.getName());
        super.show(navigation, "b");
        this.tournament = tournament;

        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "b")) {
            case "1" -> showRanking(navigation);
            case "2" -> showContenders(navigation);
            case "3" -> showMatchProgram(navigation);
            default -> navigation.get("prevMenu").show(navigation);
        }
    }

    private void showRanking(HashMap<String, Menu> navigation) {
        ui.println(tournament.getName());
        ui.waitForUser("Press enter to continue.");
        show(navigation, tournament);
    }

    private void showContenders(HashMap<String, Menu> navigation) {
        for (Team team : tournament.getContenders()) {
            ui.println(team.getName());
        }
        ui.waitForUser("Press enter to continue.");
        show(navigation, tournament);
    }

    private void showMatchProgram(HashMap<String, Menu> navigation){
        ui.println(tournament.getMatchProgram().toString());
        ui.waitForUser("Press enter to continue.");
        show(navigation, tournament);
    }

}
