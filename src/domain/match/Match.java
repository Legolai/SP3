package domain.match;

import domain.team.*;
import domain.tournament.TournamentTeam;

import java.util.Date;

public class Match {
    private MatchResult result;
    private TournamentTeam[] teams;
    private Date date;
    private TournamentTeam winner;

    public Match() {
        teams = new TournamentTeam[2];
        date = new Date();
        result = new MatchResult();
    }
    public Match(TournamentTeam[] teams) {
        this.teams = teams;
        date = new Date();
        result = new MatchResult();
    }
    public Match(TournamentTeam[] teams, Date date) {
        this.teams = teams;
        this.date = date;
        result = new MatchResult();
    }

    public TournamentTeam getTeam(int i){     //i needs to be 0 or 1
        return teams[i];
    }
    public void setTeams(TournamentTeam homeTeam, TournamentTeam guestTeam) {
        teams[0] = homeTeam;
        teams[1] = guestTeam;
    }
    public void exchangeTeamX(TournamentTeam replacingTeam, int x) {      //x has to be 0 or 1
        teams[x] = replacingTeam;
    }
    public String homeAndGuestTeam() {
        return "Home team is: "+teams[0].getName()+" and guest team is: "+teams[1].getName();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setResult(int homeScore, int guestScore) {
        result.setResult(homeScore, guestScore);
        if (homeScore > guestScore) {
            setWinner(teams[0]);
        } else {
            setWinner(teams[1]);
        }
        teams[0].getTeam().getHistory().addMatch(this);
        teams[1].getTeam().getHistory().addMatch(this);
        winner.addPoint(2);
        teams[0].addScore(result.calculateScore()[0]);
        teams[1].addScore(result.calculateScore()[1]);
    }
    public int[] getScore() {
        return result.getScore();
    }
    public int[] getCalculatedScore() {
        return result.calculateScore();
    }

    public void setWinner(TournamentTeam winner) {
        this.winner = winner;
    }
    public TournamentTeam getWinner() {
        return winner;
    }

    public String shortToString() {
        return teams[0].getName() + " vs " + teams[1].getName();
    }

    @Override
    public String toString() {
        String msg;
        if (winner == null) {
            msg = "This match is between ["+teams[0].getName()+"] and ["+teams[1].getName()+"]" +
                    " taking place the "+date.toString();
        } else {
            msg =   "This match is between ["+teams[0].getName()+"] and ["+teams[1].getName()+"]" +
                    " that took place the "+date.toString()+" and the result is: "+winner.getName() +
                    " won the match";
        }
        return msg;
    }
}
