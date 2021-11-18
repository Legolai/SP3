package ui.navigation;

import domain.tournament.KnockOutTournament;
import domain.tournament.Sport;
import domain.tournament.Tournament;

import java.util.HashMap;

public class NewTournamentMenu extends Menu {
    private final HashMap<String, Tournament> tournaments;

    public NewTournamentMenu(String name, boolean isHeaderShown, HashMap<String, Tournament> tournaments) {
        super(name, isHeaderShown, new String[]{
                "Create new knockout tournament",
                "Create new group tournament",
                "Cancel new Tournament",
        });
        this.tournaments = tournaments;
    }

    @Override
    public void show(Navigator navigation) {
        clearScreen();
        super.showMenu("b");
        switch ((ui.getUserOption("Select menu: ", getNumberOfOptions(), "b"))) {
            case "1" -> showNewKnockOutTournament(navigation);
            case "2" -> show(navigation);
            default -> navigation.goBack();
        }
    }

    private void showNewKnockOutTournament(Navigator navigation) {
        Tournament tn = createNewKnockOutTournament();
        tournaments.put(tn.getName().toLowerCase(), tn);
        navigation.setCurrentMenu("Tournaments");
        ((TournamentMenu) navigation.goManuelTo("Tournament")).show(navigation, tournaments.get(tn.getName().toLowerCase()));
    }

    private KnockOutTournament createNewKnockOutTournament() {
        Sport sport = new Sport("Fodbold");
        String nameKOTN = ui.getUserInput("Type the tournaments name: ");
        if (!tournaments.containsKey(nameKOTN.toLowerCase())) {
            int newID = 0;
            for (Tournament tn : tournaments.values()) {
                if (tn.getID() > newID) {
                    newID = tn.getID();
                }
            }

            return new KnockOutTournament(newID + 1, nameKOTN, sport);
        } else
            ui.println("Tournament already exists!");
        return createNewKnockOutTournament();
    }
}
