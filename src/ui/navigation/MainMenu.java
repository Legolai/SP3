package ui.navigation;

import domain.tournament.Tournament;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenu extends Menu {

    public MainMenu(String name, boolean isHeaderShown) {
        super(name, isHeaderShown, new String[]{
                "View tournaments",
                "Create tournament",
                "View teams",
                "Create team",
                "Quit Program"
        });
    }

    @Override
    public void show(HashMap<String, Menu> navigation) {
        clearScreen();
        super.show(navigation, "q");
        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "q")) {
            case "1" -> navigation.get("Tournaments").show(navigation);
            case "2" -> navigation.get("New Tournament").show(navigation);
            case "3" -> navigation.get("Teams").show(navigation);
            case "4" -> navigation.get("New Team").show(navigation);
            default -> navigation.get("Quit").show(navigation);
        }
    }
}
