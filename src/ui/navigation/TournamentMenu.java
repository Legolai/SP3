package ui.navigation;

import domain.match.Match;
import domain.tournament.Tournament;
import domain.tournament.TournamentTeam;

import java.util.ArrayList;

public class TournamentMenu extends Menu {
    Tournament tournament;

    public TournamentMenu(String name, boolean isHeaderShown) {
        super(name, isHeaderShown, new String[]{
                "View team ranking", // option 1
                "View all contenders", // option 2
                "Edit contenders", // option 3
                "Config finished match", // option 4
                "View match program", // option 5
                "View bracket", // option 6
                "Go back" // option go back
        });
    }

    @Override
    public void show(Navigator navigation) {

    }

    public void show(Navigator navigation, Tournament tournament) {
        clearScreen();
        super.showCustomHeader(tournament.getName());
        super.showMenu("b");
        this.tournament = tournament;

        switch (ui.getUserOption("Select menu:", getNumberOfOptions(), "b")) {
            case "1" -> showRanking(navigation);
            case "2" -> showContenders(navigation);
            case "3" -> show(navigation, tournament);  // TODO: edit contenders view
            case "4" -> showConfigMatchFinished(navigation);
            case "5" -> showMatchProgram(navigation);
            case "6" -> show(navigation, tournament); // TODO: fancy bracket
            default -> navigation.goBack();
        }
    }

    private void showConfigMatchFinished(Navigator navigation) {
        ui.newLine();
        if(tournament.getMatchProgram().getUpcomingMatches() != null){
            ArrayList<Match> matches = tournament.getMatchProgram().getUpcomingMatches();
            for (int i = 0; i < matches.size(); ++i) {
                int[] result = matches.get(i).getScore();
                String newLineIndent = "\n" + " ".repeat(3 + (i + "").length());
                ui.println("(" + (i + 1) + ") " + matches.get(i).shortToString() + (matches.get(i).getWinner() != null ? newLineIndent + result[0] + " - " + result[1] : "") + newLineIndent + matches.get(i).getDate());
            }
            ui.newLine();
            String matchIndex = ui.getUserOption("Type the index of the match (b for back):", matches.size() + 1, "b");
            if (!matchIndex.equals("b")) {
                try {
                    int index = Integer.parseInt(matchIndex);
                    ui.newLine();
                    ui.println("Selected match [" + matches.get(index - 1) + "]");
                    int homeScore = Integer.parseInt(ui.getUserInputNumber("Type the score for the home team:"));
                    int guestScore = Integer.parseInt(ui.getUserInputNumber("Type the score for the guest team:"));
                    matches.get(index - 1).setResult(homeScore, guestScore);
                    tournament.getMatchProgram().advanceKnockoutTournament();
                } catch (Exception e) {
                    ui.println(e.getMessage());
                }
            }
        }else{
            ui.println("The tournament has finished playing");
        }
        ui.waitForUser();
        show(navigation, tournament);
    }

    private void showRanking(Navigator navigation) {
        clearScreen();
        ui.println(tournament.getName());
        ui.newLine();
        ui.println("Team Ranking" + " ".repeat(12) + "| Points | Score |");
        ui.println("-".repeat(42));
        ArrayList<TournamentTeam> teams = tournament.getContenders();
        teams.sort(TournamentTeam::compareTo);
        for (int i = 0; i < teams.size(); ++i) {
            String teamName = teams.get(i).getName();
            String name = (teamName.length() > 20 ? teamName.substring(0, 17) + "..." : teamName);
            String point = teams.get(i).getPoint() + "";
            String score = teams.get(i).getScore() + "";
            ui.println("(" + (i + 1) + ") " + name + " ".repeat(20 - name.length()) + "|"
                    + " ".repeat(4 - (int) Math.floor(point.length() / 2.0)) + point
                    + " ".repeat(4 - (int) Math.ceil(point.length() / 2.0)) + "|"
                    + " ".repeat(3 - (int) Math.floor(score.length() / 2.0)) + score
                    + " ".repeat(4 - (int) Math.ceil(score.length() / 2.0)) + "|");
            ui.println("-".repeat(42));
        }
        ui.newLine();
        ui.waitForUser();
        show(navigation, tournament);
    }

    private void showContenders(Navigator navigation) {
        ui.newLine();
        StringBuilder s = new StringBuilder();
        for (TournamentTeam team : tournament.getContenders()) {
            s.append(team.getName()).append(", ");
            if (s.length() > 40) {
                ui.println(s.toString());
                s = new StringBuilder();
            }
        }
        ui.println(s.toString());
        ui.newLine();
        ui.waitForUser();
        show(navigation, tournament);
    }

    private void showMatchProgram(Navigator navigation) {
        //TODO: fix allmatches so that it does not crash when trying to get not yet made matches
        for (Match match : tournament.getMatchProgram().getUpcomingMatches()) {
            ui.println(match.shortToString() + "\n" + match.getDate());
            ui.newLine();
        }

        ui.waitForUser();
        show(navigation, tournament);
    }

}
