package domain.match;

import domain.team.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchProgram {
    private ArrayList<ArrayList<Match>> bracket;
    private ArrayList<Match> allmatches;        // is this needed?
    private ArrayList<Team> teams;
    private int[] tourSize = {2,4,8,16,32,64};
    private String scoreOrTime = "";

    public MatchProgram() {
        bracket = new ArrayList<>();
        allmatches = new ArrayList<>();
        teams = new ArrayList<>();
    }
    public MatchProgram(ArrayList<Team> teams) {
        bracket = new ArrayList<>();
        allmatches = new ArrayList<>();
        this.teams = teams;
    }

    public void setMatchType(String a) {    //a should be 'score' or 'time'
        scoreOrTime = a;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }
    public void removeTeam(String name) {
        for (Team t : teams) {
            if (t.getName().equals(name)) {
                teams.remove(t);
                break;
            }
        }
    }

    public String createMatchProgram() {
        List<Integer> toursize = Arrays.stream(tourSize).boxed().toList();
        int nrOfTeams = teams.size();
        if (!toursize.contains(nrOfTeams)) {
            return "Not valid number of teams";
        }
        for (int i = 0; i < toursize.indexOf(nrOfTeams)+1; i++) {
            ArrayList<Match> a = new ArrayList<>();
            for (int j = 0; j < nrOfTeams/(2+i*2); j++) {
                Match b = createEmptyMatch();
                a.add(b);
                allmatches.add(b);
            }
            bracket.add(a);
        }
        int counter = 0;
        for (Match m : bracket.get(0)) {
            m.setTeams(teams.get(counter),teams.get(counter+1));
            counter += 2;
        }
        return "Matchprogram has been completed, and the first "+bracket.get(0).size()+
                " matches have been scheduled (time yet to be set).";
    }

    private Match createEmptyMatch() {
        if (scoreOrTime.equals("score")) {
            return new MatchByScore();
        } else {
            return new MatchByTime();
        }
    }
    //private void createMatch(Team HomeTeam, Team GuestTeam) {}

    public void changeMatchContenders() {

    }

    public Match getMatchByID() {
        return null;
    }


    @Override
    public String toString() {
        String msg = "";
        for (Match m : allmatches) {
            msg += m.toString() + "\n";
        }
        return null;
    }

}
