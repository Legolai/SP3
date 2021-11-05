package ui.navigation;

import domain.tournament.Tournament;

import java.util.ArrayList;
import java.util.HashMap;

public class TournamentsMenu extends Menu {
    ArrayList<Tournament> tournaments;

    public TournamentsMenu(String name, boolean isHeaderShown, ArrayList<Tournament> tournaments) {
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
            case "2" -> show(navigation);
            default -> navigation.get("prevMenu").show(navigation);
        }
    }

    private void showAllTournaments(HashMap<String, Menu> navigation) {
        for (Tournament tn : tournaments) {
            ui.println(tn.toString());
        }
        ui.waitForUser("Press enter to contiune.");
        show(navigation);
    }

}
