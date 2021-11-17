package domain.match;

import domain.team.Team;
import domain.tournament.TournamentTeam;

import java.time.Instant;

public class MatchByTime extends Match {
    private int minutes;

    public MatchByTime(int matchCount, String tournamentName) {
        super(matchCount ,tournamentName);
        minutes = 15;
    }
    public MatchByTime(TournamentTeam[] teams, int minutes) {
        super(teams);
        this.minutes = minutes;
    }
    public MatchByTime(TournamentTeam[] teams, Instant date, int minutes) {
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
