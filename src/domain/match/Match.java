package domain.match;

import domain.tournament.TournamentTeam;

import java.time.LocalDateTime;


public class Match {
    private String ID;
    private final MatchResult result;
    private final TournamentTeam[] teams;
    private LocalDateTime date;
    private TournamentTeam winner;

    public Match(int matchCount, String tournamentName) {
        this.ID = matchCount + "_" + tournamentName;
        teams = new TournamentTeam[2];
        date = LocalDateTime.now();
        result = new MatchResult();
    }

    public Match(TournamentTeam[] teams) {
        this.teams = teams;
        date = LocalDateTime.now();
        result = new MatchResult();
    }

    public Match(TournamentTeam[] teams, LocalDateTime date) {
        this.teams = teams;
        this.date = date;
        result = new MatchResult();
    }

    public Match(int matchCount, String tournamentName, TournamentTeam[] teams, LocalDateTime date) {
        this.ID = matchCount + "_" + tournamentName;
        this.teams = teams;
        this.date = date;
        result = new MatchResult();
    }

    public Match(int matchCount, String tournamentName, TournamentTeam homeTeam, TournamentTeam guestTeam, LocalDateTime date) {
        this.ID = matchCount + "_" + tournamentName;
        this.teams = new TournamentTeam[]{homeTeam, guestTeam};
        this.date = date;
        result = new MatchResult();
    }


    public TournamentTeam getTeam(int i) {     //I needs to be 0 or 1
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
        return "Home team is: " + teams[0].getName() + " and guest team is: " + teams[1].getName();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setResult(int homeScore, int guestScore, boolean willAddPoints) {
        result.setResult(homeScore, guestScore);
        if (homeScore > guestScore) {
            setWinner(teams[0]);
        } else {
            setWinner(teams[1]);
        }
        if (willAddPoints) {
            teams[0].getTeam().getHistory().addMatch(this);
            teams[1].getTeam().getHistory().addMatch(this);
            winner.addPoint(2);
            teams[0].addScore(result.calculateScore()[0]);
            teams[1].addScore(result.calculateScore()[1]);
        }
    }

    public int[] getScore() {
        return result.getScore();
    }

    public int[] getCalculatedScore() {
        return result.calculateScore();
    }

    public TournamentTeam getWinner() {
        return winner;
    }

    public void setWinner(TournamentTeam winner) {
        this.winner = winner;
    }

    public String shortToString() {
        String homeTeam = (teams[0] == null ? "TBD" : teams[0].getName());
        String guestTeam = (teams[1] == null ? "TBD" : teams[1].getName());
        return date.toString() + " " + homeTeam + " [" + result.homeScore + "]" +
                " vs " + "[" + result.guestScore + "] " + guestTeam;
    }

    @Override
    public String toString() {
        String msg;
        if (winner == null) {
            msg = "This match is between [" + teams[0].getName() + "] and [" + teams[1].getName() + "]" +
                    " taking place the " + date.toString();
        } else {
            msg = "This match is between [" + teams[0].getName() + "] and [" + teams[1].getName() + "]" +
                    " that took place the " + date.toString() + " and the result is: " + winner.getName() +
                    " won the match";
        }
        return msg;
    }

    public String getID() {
        return this.ID;
    }

    public String getType() {
        return "SCORE";
    }
}
