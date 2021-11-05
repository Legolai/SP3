package domain.match;

import domain.team.Team;
import java.util.Date;

public class MatchByScore extends Match {
    int guestScore;
    int homeScore;
    private Team winner;


    public MatchByScore() {
        super();
    }
    public MatchByScore(Team[] teams) {
        super(teams);
    }
    public MatchByScore(Team[] teams, Date date) {
        super(teams, date);
    }

    @Override
    public void setResult(int homeScore, int guestScore) {
        this.guestScore = guestScore - homeScore;
        this.homeScore = homeScore - guestScore;
    }

    @Override
    public int[] getScore() {
        return new int[]{homeScore, guestScore};
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
