package domain.match;

import domain.team.Team;

import java.util.Date;

public abstract class Match {

    private MatchResult result;
    private final Team[] teams;
    private final Date date;
    private Team winner;


    public Match(Team[] teams, Date date) {
        this.teams = teams;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setResult(int homeScore, int guestScore) {
        result.setResult(homeScore, guestScore);
    }

    public int[] getScore() {
        return result.getScore();
    }

    public int[] getCalculatedScore() {
        return result.calculateScore();
    }

    public abstract void setWinner(Team winner);

    public abstract Team getWinner();
}
