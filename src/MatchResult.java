import java.util.Date;

public class MatchResult extends Match {
    int homeScore;
    int guestScore;

    public MatchResult(Team[] teams, Date date) {
        super(teams, date);
    }


    @Override
    public void setResult(int homeScore, int guestScore) {
        this.guestScore = guestScore - homeScore;
        this.homeScore = homeScore - guestScore;
    }

    public int[] calculateScore(int homeScore, int guestScore){
        int[] scores = new int[]{homeScore, guestScore};
        return scores;
    }

    @Override
    public int[] getScore() {
        return new int[0];
    }

    @Override
    public void setWinner(Team winner) {

    }

    @Override
    public Team getWinner() {
        return null;
    }

}
