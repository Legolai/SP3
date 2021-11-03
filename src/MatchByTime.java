import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MatchByTime extends Match {
    private int minutes;
    private int homeScore;
    private int guestScore;
    private Team winner;
    HashMap<String, Integer> teamScores = new HashMap<>();


    public MatchByTime(Team[] teams, Date date, int minutes) {
        super(teams, date);
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public void setResult(int homeScore, int guestScore) {
        this.guestScore = guestScore - homeScore;
        this.homeScore = homeScore - guestScore;
    }


    @Override
    public int[] getScore() {
        int[] scores = new int[]{homeScore, guestScore};
        return scores;
    }

    @Override
    public void setWinner(Team winner) {
        this.winner = winner;
    }

    @Override
    public Team getWinner() {
        return winner;
    }
}
