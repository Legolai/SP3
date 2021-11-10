package domain.tournament;

import domain.team.Team;

public class TournamentTeam {
    private final Team team;
    private int point;
    private int score;

    public TournamentTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public int getPoint() {
        return point;
    }

    public int getScore() {
        return score;
    }

    public void addPoint(int pointAmount) {
        this.point = point + pointAmount;
    }

    public void addScore(int scoreAmount) {
        this.score = score + scoreAmount;
    }

    public String getName() {
        return team.getName();
    }

    public int compareTo(TournamentTeam otherTeam) {
        if (this.point != otherTeam.getPoint()) {
            return otherTeam.getPoint() - this.point;
        } else if (this.score != otherTeam.getScore()) {
            return otherTeam.getScore() - this.score;
        } else {
            return otherTeam.getName().compareTo(this.team.getName());
        }
    }
}
