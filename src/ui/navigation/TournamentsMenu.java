package ui.navigation;

import domain.team.Team;
import domain.tournament.Tournament;

import java.util.ArrayList;
import java.util.HashMap;

public class TournamentsMenu extends Menu {
    private final HashMap<String, Tournament> tournaments;

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
        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "b")) {
            case "1" -> showAllTournaments(navigation);
            case "2" -> goToTournament(navigation);
            default -> navigation.goBack();
        }
    }

    private void goToTournament(Navigator navigation) {
        StringBuilder s = new StringBuilder();
        int memberNum = 1;
        ArrayList<Tournament> tns = new ArrayList<>(tournaments.values());
        for (Tournament tn: tns) {
            s.append("(").append(memberNum).append(")").append(tn.getName()).append(", ");
            if (s.length() > 40) {
                ui.println(s.toString());
                s = new StringBuilder();
            }
            ++memberNum;
        }
        ui.println(s.toString());

        String memberIndex = ui.getUserOption("Type the index of the team (b for back):", tournaments.size()+1,"b");

        try{
            int index = Integer.parseInt(memberIndex);
            String tournamentName = tns.get(index-1).getName().toLowerCase();
            if (tournaments.containsKey(tournamentName)) {
                ((TournamentMenu) navigation.goManuelTo("Tournament")).show(navigation, tournaments.get(tournamentName));
            } else {
                ui.println("The tournament " + tournamentName + " does not exist");
                ui.waitForUser();
                show(navigation);
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    private void showAllTournaments(Navigator navigation) {
        ui.newLine();
        StringBuilder s = new StringBuilder();
        for (String key : tournaments.keySet()) {
            s.append(tournaments.get(key).getName()).append(", ");
            if (s.length() > 40) {
                ui.println(s.toString());
                s = new StringBuilder();
            }
        }
        ui.println(s.toString());
        ui.newLine();
        ui.waitForUser();
        show(navigation);
    }

}
