package domain.tournament;

import domain.team.Team;

public class TournamentTeam {
    private final Team team;
    private int point;
    private int score;

    public TournamentTeam(Team team) {
        this.team = team;
    }

    public void addPoint(int pointAmount) {
        this.point = point + pointAmount;
    }

    public void addScore(int scoreAmount) {
        this.score = score + scoreAmount;
    }
}
