import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TournamentHistory {
    //LinkedHashMap<String,Integer> a;      this for teamHistory
    private ArrayList<Match> matches;       //maybe not arraylist to also keep track of final/semi etc.

    public TournamentHistory() {
        this.matches = new ArrayList<Match>();
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void addMatch(Match a) {
        matches.add(a);
    }
}
