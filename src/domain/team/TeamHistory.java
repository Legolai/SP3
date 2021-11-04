package domain.team;

import domain.match.Match;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TeamHistory {

    private LinkedHashMap<String, Integer> teamStreak;
    private LinkedHashMap<String, Integer> tournamentHistoryResults;
    private ArrayList<Match> matches;

    private void addMatch(Match match) {
        matches.add(match);
    }

    public void addTournamentHistoryResults(String tournament, int ranking) {
        tournamentHistoryResults.put(tournament, ranking);
    }

    private void updateTeamStreak() {
        for (Match match : matches ){
            teamStreak.put("score:",match.getScore()[0]);
        }
    }

    @Override
    public String toString() { //todo: make more specific
        return "domain.Team.TeamHistory{" +
                "teamStreak=" + teamStreak +
                ", tournamentHistoryResults=" + tournamentHistoryResults +
                ", matches=" + matches +
                '}';
    }
}
