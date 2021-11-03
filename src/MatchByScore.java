import java.util.Date;

public class MatchByScore extends Match {
    int guestScore;
    int homeScore;
    private Team winner;

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
        int[] scores = new int[]{homeScore, guestScore};
        return scores;
    }


    @Override
    public Team getWinner() {
        return winner;
    }


    @Override
    public void setWinner(Team winner) {
        this.winner = winner;
    }


}
