import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TeamHistory {
    private LinkedHashMap<String, Integer> teamStreak;
    private LinkedHashMap<String, Integer> tournamentHistoryResults;
    private ArrayList<String> matches; //todo: change String to Match.

    private void addMatch(String match){//todo: change String to Match
        matches.add(match);
    }

    public void addTournamentHistoryResults(){


    }

    private void updateTeamStreak(){

    }

    @Override
    public String toString() { //todo: make more specific
        return "TeamHistory{" +
                "teamStreak=" + teamStreak +
                ", tournamentHistoryResults=" + tournamentHistoryResults +
                ", matches=" + matches +
                '}';
    }
}
