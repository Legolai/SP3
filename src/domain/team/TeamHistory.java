package domain.team;

import domain.match.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TeamHistory {
    private LinkedHashMap<String, Integer> teamStreak;
    private LinkedHashMap<String, Integer> tournamentHistoryResults;
    private ArrayList<Match> matches;
    private Team team;

    public TeamHistory(Team team) {
        teamStreak = new LinkedHashMap<>();
        tournamentHistoryResults = new LinkedHashMap<>();
        matches = new ArrayList<>();
        this.team = team;
        teamStreak.put("Wins", 0);
        teamStreak.put("Draws", 0);
        teamStreak.put("Losses", 0);
    }

    public LinkedHashMap<String, Integer> getTeamStreak() {
        return teamStreak;
    }

    public void addMatch(Match match) {
        matches.add(match);
        updateTeamStreak(match);
    }

    public void addTournamentHistoryResults(String tournament, int ranking) {
        tournamentHistoryResults.put(tournament, ranking);
    }

    private void updateTeamStreak(Match match) {
        String s;
        if (team.equals(match.getWinner().getTeam())) {
            s = "Wins";
        } else if (match.getWinner() == null) {
            s = "Draws";
        } else {
            s = "Losses";
        }
        teamStreak.put(s, teamStreak.get(s) + 1);
    }

    public void setTeamStreak(LinkedHashMap<String, Integer> teamStreak) {
        this.teamStreak = teamStreak;
    }

    public LinkedHashMap<String, Integer> getTournamentHistoryResults() {
        return tournamentHistoryResults;
    }

    public void setTournamentHistoryResults(LinkedHashMap<String, Integer> tournamentHistoryResults) {
        this.tournamentHistoryResults = tournamentHistoryResults;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() { //todo: make more specific
        return teamStreak + ", " + tournamentHistoryResults + ", " + matches;
    }
}
