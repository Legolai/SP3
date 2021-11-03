public class TournamentTeam {
    private Team team;
    private int point;
    private int score;

    public TournamentTeam(Team team) {
        this.team = team;
    }

    public void addPoint(int amount) {
        this.point = point + amount;
    }

    public void addScore(int amount) {
        this.score = score + amount;
    }
}
