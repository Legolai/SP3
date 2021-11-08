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
    public void show(Navigator navigation) {

    }

    public void show(Navigator navigation, Tournament tournament) {
        clearScreen();
        super.showCustomHeader(tournament.getName());
        super.showMenu( "b");
        this.tournament = tournament;

        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "b")) {
            case "1" -> showRanking(navigation);
            case "2" -> showContenders(navigation);
            case "3" -> showMatchProgram(navigation);
            default -> navigation.goBack();
        }
    }

    private void showRanking(Navigator navigation) {
        ui.println(tournament.getName());
        ui.waitForUser();
        show(navigation, tournament);
    }

    private void showContenders(Navigator navigation) {
        for (Team team : tournament.getContenders()) {
            ui.println(team.getName());
        }
        ui.waitForUser();
        show(navigation, tournament);
    }

    private void showMatchProgram(Navigator navigation){
        ui.println(tournament.getMatchProgram().toString());
        ui.waitForUser();
        show(navigation, tournament);
    }

}
