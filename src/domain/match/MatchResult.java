package domain.match;

import java.util.Date;

public class MatchResult {
    int homeScore;
    int guestScore;

    public MatchResult() {
        homeScore = 0;
        guestScore = 0;
    }
    public void setResult(int homeScore, int guestScore) {
        this.guestScore = guestScore;
        this.homeScore = homeScore;
    }

    public int[] calculateScore(){
        return new int[]{homeScore - guestScore, guestScore - homeScore};
    }

    public int[] getScore() {
        return new int[]{homeScore, guestScore};
    }

}

