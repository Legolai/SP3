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
        this.team=team;
    }

    private void addMatch(Match match) {
        matches.add(match);
        updateTeamStreak(match);
    }

    public void addTournamentHistoryResults(String tournament, int ranking) {
        tournamentHistoryResults.put(tournament, ranking);
    }

    private void updateTeamStreak(Match match) {
        String s;
            if(team.equals(match.getWinner())) {
                s="Wins";
            }else if(match.getWinner() == null) {
                s = "Draws";
            }else {
                s = "Losses";
            }
                teamStreak.put(s, teamStreak.get(s) + 1);
        }

    @Override
    public String toString() { //todo: make more specific
        return ", tournamentHistoryResults=" + tournamentHistoryResults +
                ", matches=" + matches +
                '}';
    }
}
