package domain.match;

import domain.team.Team;
import domain.tournament.TournamentTeam;

import java.util.Date;
import java.util.HashMap;

public class MatchByTime extends Match {
    private int minutes;

    public MatchByTime() {
        super();
        minutes = 15;
    }
    public MatchByTime(TournamentTeam[] teams, int minutes) {
        super(teams);
        this.minutes = minutes;
    }
    public MatchByTime(TournamentTeam[] teams, Date date, int minutes) {
        super(teams, date);
        this.minutes = minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    public int getMinutes() {
        return minutes;
    }

}
