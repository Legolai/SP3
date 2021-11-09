package ui.navigation;

import domain.team.Team;
import domain.tournament.Tournament;

import java.util.HashMap;

public class TournamentsMenu extends Menu {
    HashMap<String, Tournament> tournaments;

    public TournamentsMenu(String name, boolean isHeaderShown, HashMap<String, Tournament> tournaments) {
        super(name, isHeaderShown, new String[]{
                "View all tournaments",
                "Select tournament",
                "Go back",
        });
        this.tournaments = tournaments;
    }

    @Override
    public void show(Navigator navigation) {
        clearScreen();
        super.showMenu("b");
        switch (ui.getUserOption("Select menu: ", getNumberOfOptions(), "b")) {
            case "1" -> showAllTournaments(navigation);
            case "2" -> goToTournament(navigation);
            default -> navigation.goBack();
        }
    }

    private void goToTournament(Navigator navigation) {
        String tournamentName = ui.getUserInput("Type the tournament's name: ").toLowerCase();
        if (tournaments.containsKey(tournamentName)) {
            ((TournamentMenu) navigation.goManuelTo("Tournament")).show(navigation, tournaments.get(tournamentName));
        } else {
            ui.println("The tournament " + tournamentName + " does not exist");
            ui.waitForUser();
            show(navigation);
        }
    }

    private void showAllTournaments(Navigator navigation) {
        ui.newLine();
        String s = "";
        for (String key : tournaments.keySet()) {
            s += tournaments.get(key).getName() + ", ";
            if(s.length() > 40){ ui.println(s); s="";}
        }
        ui.newLine();
        ui.waitForUser();
        show(navigation);
    }

}
