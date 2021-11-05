package ui.navigation;

import domain.tournament.KnockOutTournament;
import domain.tournament.Sport;
import domain.tournament.Tournament;

import java.util.ArrayList;
import java.util.HashMap;

public class NewTournamentMenu extends Menu {
    private ArrayList<Tournament> tournaments;

    public NewTournamentMenu(String name, boolean isHeaderShown, ArrayList<Tournament> tournaments) {
        super(name, isHeaderShown, new String[]{
                "Create new knockout tournament",
                "Create new group tournament",
                "Cancel new Tournament",
        });
        this.tournaments = tournaments;
    }

    @Override
    public void show(HashMap<String, Menu> navigation) {
        Tournament tournament;
        Sport sport = new Sport("Fodbold");
        clearScreen();
        super.show(navigation, "b");
        switch ((ui.getUserOption("Select menu: ", getNumberOfOptions(), "b"))) {
            case "1" -> tournaments.add(createNewKnockOutTournament());
            case "2" -> show(navigation);
            default -> navigation.get("prevMenu").show(navigation);

        }


    }

    private KnockOutTournament createNewKnockOutTournament() {
        String nameKOTN = ui.getUserInput("Type the tournaments name: ").toLowerCase();
        return new KnockOutTournament(nameKOTN, new Sport("Fodbold"));
    }
}
