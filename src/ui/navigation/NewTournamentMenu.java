package ui.navigation;

import domain.tournament.KnockOutTournament;
import domain.tournament.Sport;
import domain.tournament.Tournament;

import java.util.HashMap;

public class NewTournamentMenu extends Menu {
    private HashMap<String, Tournament> tournaments;

    public NewTournamentMenu(String name, boolean isHeaderShown, HashMap<String, Tournament> tournaments) {
        super(name, isHeaderShown, new String[]{
                "Create new knockout tournament",
                "Create new group tournament",
                "Cancel new Tournament",
        });
        this.tournaments = tournaments;
    }

    @Override
    public void show(HashMap<String, Menu> navigation) {
        clearScreen();
        super.show(navigation, "b");
        switch ((ui.getUserOption("Select menu: ", getNumberOfOptions(), "b"))) {
            case "1" -> {
                Tournament tn = createNewKnockOutTournament();
                tournaments.put(tn.getName().toLowerCase(), tn);
            }
            case "2" -> show(navigation);
            default -> navigation.get("prevMenu").show(navigation);
        }
    }

    private KnockOutTournament createNewKnockOutTournament() {
        Sport sport = new Sport("Fodbold");
        String nameKOTN = ui.getUserInput("Type the tournaments name: ");
        if (!tournaments.containsKey(nameKOTN.toLowerCase()))
            return new KnockOutTournament(nameKOTN, sport);
        else
            ui.println("Tournament already exists!");
            return createNewKnockOutTournament();
    }
}
