package ui.navigation;

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
    public void show(HashMap<String, Menu> navigation) {
        clearScreen();
        super.show(navigation, "b");
        switch (ui.getUserOption("Select menu: ", getNumberOfOptions(), "b")) {
            case "1" -> showAllTournaments(navigation);
            case "2" -> goToTournament(navigation);
            default -> navigation.get("prevMenu").show(navigation);
        }
    }

    private void goToTournament(HashMap<String, Menu> navigation) {
        String tournamentName = ui.getUserInput("Type the tournament's name: ").toLowerCase();
        if (tournaments.containsKey(tournamentName)) {
            ((TournamentMenu) navigation.get("Tournament")).show(navigation, tournaments.get(tournamentName));
        } else {
            ui.println("The tournament " + tournamentName + " does not exist");
            ui.waitForUser();
            show(navigation);
        }
    }

    private void showAllTournaments(HashMap<String, Menu> navigation) {
        for (String key : tournaments.keySet()) {
            ui.println(tournaments.get(key).getName());
        }
        ui.waitForUser();
        show(navigation);
    }

}
