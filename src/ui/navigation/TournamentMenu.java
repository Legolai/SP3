package ui.navigation;

import domain.match.Match;
import domain.team.Team;
import domain.tournament.Tournament;

import java.util.ArrayList;

public class TournamentMenu extends Menu {
    Tournament tournament;

    public TournamentMenu(String name, boolean isHeaderShown) {
        super(name, isHeaderShown, new String[]{
                "View team ranking",
                "View all contenders",
                "Edit contenders",
                "View match program",
                "View bracket",
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
            case "3" -> show(navigation, tournament);
            case "4" -> showMatchProgram(navigation);
            case "5" -> show(navigation, tournament);
            default -> navigation.goBack();
        }
    }

    private void showRanking(Navigator navigation) {
        clearScreen();
        ui.println(tournament.getName());
        ui.newLine();
        ui.println("Team Ranking" + " ".repeat(12) + "| Points | Score |");
        ui.println("-".repeat(42));
        ArrayList<Team> teams = tournament.getContenders();
        for (int i = 0; i < teams.size(); ++i) {
            String teamName = teams.get(i).getName();
            String name = (teamName.length() > 20 ? teamName.substring(0,17)+"..." : teamName);
            // TODO: handel score and point size and padding
            ui.println("(" + (i+1) + ") " + name + " ".repeat(20 - name.length()) + "|"+ " ".repeat(3) + 0 + " ".repeat(4) +"|" + " ".repeat(3) + 0 + " ".repeat(3) + "|");
            ui.println("-".repeat(42));
        }
        ui.newLine();
        ui.waitForUser();
        show(navigation, tournament);
    }

    private void showContenders(Navigator navigation) {
        ui.newLine();
        String s = "";
        for (Team team : tournament.getContenders()) {
            s += team.getName() + ", ";
            if(s.length() > 40){ ui.println(s); s="";}
        }
        ui.newLine();
        ui.waitForUser();
        show(navigation, tournament);
    }

    private void showMatchProgram(Navigator navigation){
        tournament.createMatchProgram("score");
        for (Match match : tournament.getMatchProgram().getAllMatchesORUpcomingMatches(0)) {
            ui.println("| " + match.getTeam(0).getName().replace(" ", "").substring(0,3).toUpperCase() + " vs " + match.getTeam(1).getName().replace(" ", "").substring(0,3).toUpperCase() + " |");
            ui.println("| " + match.getDate().toString() + " |");
        }

        ui.waitForUser();
        show(navigation, tournament);
    }

}
