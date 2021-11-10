package domain.match;

import domain.team.Team;
import domain.tournament.TournamentTeam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatchProgram {
    private ArrayList<ArrayList<Match>> knockoutBracket;
    private ArrayList<Match> allMatches;        // is this needed?
    private ArrayList<Match> upcomingMatches;   // matches to be played, have to finish before next round
    private int currentRound;
    private ArrayList<TournamentTeam> teams;
    //private ArrayList<Team> addedRemovedTeams;
    private int[] tourSize = {2, 4, 8, 16, 32, 64};
    private String scoreOrTime = "";

    public MatchProgram() {
        knockoutBracket = new ArrayList<>();
        allMatches = new ArrayList<>();
        teams = new ArrayList<>();
        //addedRemovedTeams = new ArrayList<>();
    }

    public MatchProgram(ArrayList<TournamentTeam> teams) {
        knockoutBracket = new ArrayList<>();
        allMatches = new ArrayList<>();
        this.teams = teams;
        //addedRemovedTeams = new ArrayList<>();
    }

    public void setMatchType(String a) {
        scoreOrTime = a;
    }   //a should be 'score' or 'time'

    public void addTeam(TournamentTeam team) {
        teams.add(team);
    }

    public void removeTeam(String name) {
        for (TournamentTeam t : teams) {
            if (t.getName().equals(name)) {
                teams.remove(t);
                break;
            }
        }
    }

    public void removeTeam(Team team) {
        for (TournamentTeam t : teams) {
            if (t.getTeam().equals(team)) {
                teams.remove(t);
                break;
            }
        }
    }

    public ArrayList<TournamentTeam> getTeams() {
        return teams;
    }

    public ArrayList<Match> getAllMatches() {
        return allMatches;
    }

    public ArrayList<Match> getUpcomingMatches() {
        return upcomingMatches;
    }


    public void advanceKnockoutTournament() {
        ArrayList<TournamentTeam> wonTeams = new ArrayList<>();
        if (upcomingMatches.size() == 1) {
            if (upcomingMatches.get(0).getWinner() == null) {
                return;
            }
            upcomingMatches = null;
            return;
        }
        for (Match a : upcomingMatches) {
            if (a.getWinner() == null) {
                return;
            }   // if a match doesnt have a winner, returns string "not all matches have finished"
            wonTeams.add(a.getWinner());
        }   // checks if the upcomingMatches have been finished, if not can't continue
        currentRound += 1;
        int counter = 0;
        for (Match a : knockoutBracket.get(currentRound)) {
            a.setTeams(wonTeams.get(counter), wonTeams.get(counter + 1));
            counter += 2;
        }
        upcomingMatches = knockoutBracket.get(currentRound);
    }


    public String createMatchProgram() {
        //List<Integer> toursize = (List<Integer>) Arrays.stream(tourSize).boxed();//toList();
        List<Integer> toursize = Arrays.stream(tourSize).boxed().collect(Collectors.toList());
        int nrOfTeams = teams.size();
        if (!toursize.contains(nrOfTeams)) {
            return "Not valid number of teams";
        }

        for (int i = 0; i < toursize.indexOf(nrOfTeams) + 1; i++) {
            ArrayList<Match> a = new ArrayList<>();
            for (int j = 0; j < nrOfTeams / (2 + i * 2); j++) {
                Match b = createEmptyMatch();
                a.add(b);
                allMatches.add(b);
            }
            knockoutBracket.add(a);
        }
        upcomingMatches = knockoutBracket.get(0);
        currentRound = 0;
        int counter = 0;

        //TODO: random team gen
        for (Match m : upcomingMatches) {   // sets the first round of matches
            m.setTeams(teams.get(counter), teams.get(counter + 1));
            counter += 2;
        }
        return "Matchprogram has been completed, and the first " + knockoutBracket.get(0).size() +
                " matches have been scheduled (time yet to be set).";
    }

    private Match createEmptyMatch() {
        if (scoreOrTime.equals("score")) {
            return new Match();
        } else {
            return new MatchByTime();
        }
    }
    //private void createMatch(Team HomeTeam, Team GuestTeam) {}

    public void switchTeams(Match m1, int t1, Match m2, int t2) {   // t1 and t2 needs to be 0 or 1
        changeMatchContenders(m1, m2.getTeam(t2), t1);    // match 1 gets new team from match 2 that is t2, replacing t1 in match 1
        changeMatchContenders(m2, m1.getTeam(t1), t2);    // reverse of above
    }

    public void changeMatchContenders(Match m, TournamentTeam t, int ab) {    // ab has to be 0 or 1
        for (Match a : allMatches) {
            if (a.equals(m)) {
                a.exchangeTeamX(t, ab);
                break;
            }
        }
        checkIfInTeams(t);
    }

    private void checkIfInTeams(TournamentTeam t) {      // adds team t if not found in the list teams
        for (TournamentTeam a : teams) {
            if (a.equals(t)) {      // team t already in teams, so just end method
                return;
            }
        }
        teams.add(t);
    }

    @Override
    public String toString() {
        String msg = "";
        for (Match m : allMatches) {
            msg += m.toString() + "\n";
        }
        return msg;
    }

}
