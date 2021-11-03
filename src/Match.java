import java.util.Date;
import java.util.Map;

public abstract class Match {

    private MatchResult result;
    private Team[] teams;
    private Date date;
    private Team winner;


    public Match(Team[] teams, Date date) {
        this.teams = teams;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public abstract void setResult(int HomeScore, int GuestScore);

    public abstract int[] getScore();

    public abstract void setWinner(Team winner);

    public abstract Team getWinner();
}
