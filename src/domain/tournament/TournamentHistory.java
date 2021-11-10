package domain.tournament;

import domain.match.Match;

import java.util.ArrayList;

public class TournamentHistory {
    //LinkedHashMap<String,Integer> a;      this for teamHistory
    private final ArrayList<Match> matches;       //maybe not arraylist to also keep track of final/semi etc.

    public TournamentHistory() {
        this.matches = new ArrayList<>();
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void addMatch(Match a) {
        matches.add(a);
    }
}
