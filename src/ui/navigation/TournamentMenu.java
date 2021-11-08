package ui.navigation;

import domain.team.Team;
import domain.tournament.Tournament;

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
        ui.println("Team Ranking" + " ".repeat(10) + "| Points | Score |");
        ui.println("-".repeat(40));
        for (Team team: tournament.getContenders()) {
            String teamName = team.getName();
            String name = (teamName.length() > 20 ? teamName.substring(0,17)+"..." : teamName);
            // TODO: handel score and point size and padding
            ui.println("| " + name + " ".repeat(20 - name.length()) + "|"+ " ".repeat(3) + 0 + " ".repeat(4) +"|" + " ".repeat(3) + 0 + " ".repeat(3) + "|");
            ui.println("-".repeat(40));
        }
        ui.newLine();
        ui.waitForUser();
        show(navigation, tournament);
    }

    private void showContenders(Navigator navigation) {
        int columns = 0;
        ui.newLine();
        for (Team team : tournament.getContenders()) {
            ui.print(team.getName() + "," + (columns > 4 ? "\n" : " "));
            if (columns > 4) {
                columns = 0;
                continue;
            }
            ++columns;
        }
        ui.newLine();
        ui.waitForUser();
        show(navigation, tournament);
    }

    private void showMatchProgram(Navigator navigation){
        ui.println(tournament.getMatchProgram().toString());
        ui.waitForUser();
        show(navigation, tournament);
    }

}
