package ui.navigation;

import domain.match.Match;
import domain.team.Player;
import domain.team.Team;
import domain.tournament.Tournament;
import domain.tournament.TournamentTeam;

import java.util.ArrayList;
import java.util.HashMap;

public class TournamentMenu extends Menu {
    private Tournament tournament;
    private HashMap<String, Team> teams;

    public TournamentMenu(String name, boolean isHeaderShown, HashMap<String, Team> teams) {
        super(name, isHeaderShown, new String[]{
                "View team ranking", // option 1
                "View all contenders", // option 2
                "Edit contenders", // option 3
                "Config finished match", // option 4
                "View match program", // option 5
                "View bracket", // option 6
                "Go back" // option go back
        });
        this.teams = teams;
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
            case "3" -> showEditContenders(navigation);  // TODO: edit contenders view
            case "4" -> showConfigMatchFinished(navigation);
            case "5" -> showMatchProgram(navigation);
            case "6" -> show(navigation, tournament); // TODO: fancy bracket
            default -> navigation.goBack();
        }
    }

    private void showEditContenders(Navigator navigation) {
        clearScreen();
        super.showCustomHeader(tournament.getName());
        ui.printOptions(new String[]{
                "Add contender" + (tournament.getMatchProgram() != null ?"(The tournament has begun)":""),
                "Remove contender" + (tournament.getMatchProgram() != null ?"(The tournament has begun)":""),
                "Go back"
        }, "b");
        switch (ui.getUserOption("Select action:", getNumberOfOptions(), "b")) {
            case "1" -> showAddNewContender(navigation);
            case "2" -> showRemoveContender(navigation);
            default -> show(navigation, tournament);
        }
    }

    private void showRemoveContender(Navigator navigation) {
        StringBuilder s = new StringBuilder();
        int memberNum = 1;
        for (TournamentTeam tm : tournament.getContenders()) {
            s.append("(").append(memberNum).append(")").append(tm.getName()).append(", ");
            if (s.length() > 40) {
                ui.println(s.toString());
                s = new StringBuilder();
            }
            ++memberNum;
        }
        ui.println(s.toString());


        String memberIndex = ui.getUserOption("Type the index of the team (b for back):", tournament.getContenders().size() + 1, "b");
        if (!memberIndex.equals("b")) {
            try {
                int index = Integer.parseInt(memberIndex);
                ui.newLine();
                TournamentTeam team = tournament.getContenders().get(index - 1);
                ui.println("Selected Team [" + team.getName() + "]");
                if (ui.getUserOption("Are you sure you want to remove this team? (y/n):", new String[]{"y", "n"}).equalsIgnoreCase("y")) {
                    tournament.removeContender(team);
                    ui.println("The team " + team.getName() + " has been removed from the team");
                }
            } catch (Exception e) {
                ui.println(e.getMessage());
            }
        }
        ui.waitForUser();
        showEditContenders(navigation);
    }

    private void showAddNewContender(Navigator navigation) {
        StringBuilder s = new StringBuilder();
        int memberNum = 1;
        ArrayList<Team> tms = new ArrayList<>(teams.values());
        for (Team tm: tms) {
            s.append("(").append(memberNum).append(")").append(tm.getName()).append(", ");
            if (s.length() > 40) {
                ui.println(s.toString());
                s = new StringBuilder();
            }
            ++memberNum;
        }
        ui.println(s.toString());

        String memberIndex = ui.getUserOption("Type the index of the team (b for back):", teams.size()+1,"b");
        if (!memberIndex.equals("b")) {
            try {
                int index = Integer.parseInt(memberIndex);
                ui.newLine();
                Team team = teams.get(tms.get(index).getName().toLowerCase());
                ui.println("Selected Team [" + team.getName() + "]");
                if (ui.getUserOption("Are you sure you want to add this team? (y/n):", new String[]{"y", "n"}).equalsIgnoreCase("y")) {
                    tournament.addContender(new TournamentTeam(team));
                    ui.println("The team " + team.getName() + " has been add from the tournament");
                }
            } catch (Exception e) {
                ui.println(e.getMessage());
            }
        }
        ui.waitForUser();
        showEditContenders(navigation);
    }


    private void showConfigMatchFinished(Navigator navigation) {
        ui.newLine();

        if (tournament.getMatchProgram().getUpcomingMatches() != null) {
            ArrayList<Match> matches = tournament.getMatchProgram().getUpcomingMatches();
            for (int i = 0; i < matches.size(); ++i) {
                String newLineIndent = "\n" + " ".repeat(3 + (i + "").length());
                ui.println("(" + (i + 1) + ") " + matches.get(i).shortToString() + (matches.get(i).getWinner() != null ? newLineIndent + "Match is finished" : ""));
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
                    matches.get(index - 1).setResult(homeScore, guestScore, true);
                    tournament.getMatchProgram().advanceKnockoutTournament();
                } catch (Exception e) {
                    ui.println(e.getMessage());
                }
            }
        } else {
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
        ArrayList<TournamentTeam> tnteams = tournament.getContenders();
        tnteams.sort(TournamentTeam::compareTo);
        for (int i = 0; i < tnteams.size(); ++i) {
            String teamName = tnteams.get(i).getName();
            String name = (teamName.length() > 20 ? teamName.substring(0, 17) + "..." : teamName);
            String point = tnteams.get(i).getPoint() + "";
            String score = tnteams.get(i).getScore() + "";
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
        for (Match match : tournament.getMatchProgram().getAllMatches()) {
            ui.println(match.shortToString() + "\n" + match.getDate());
            ui.newLine();
        }

        ui.waitForUser();
        show(navigation, tournament);
    }

}
