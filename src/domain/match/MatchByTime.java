package domain.match;

import domain.team.Team;
import java.util.Date;
import java.util.HashMap;

public class MatchByTime extends Match {
    private int minutes;
    private Team winner;
    HashMap<String, Integer> teamScores = new HashMap<>();


    public MatchByTime() {
        super();
        minutes = 15;
    }
    public MatchByTime(Team[] teams, int minutes) {
        super(teams);
        this.minutes = minutes;
    }
    public MatchByTime(Team[] teams, Date date, int minutes) {
        super(teams, date);
        this.minutes = minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    public int getMinutes() {
        return minutes;
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
