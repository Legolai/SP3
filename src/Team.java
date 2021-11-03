import java.util.ArrayList;

public class Team {
    private String name;
    ArrayList<Player> teamMembers;
    TeamHistory history;

    public Team(String name, ArrayList<Player> teamMembers, TeamHistory history) {
        this.name = name;
        this.teamMembers = teamMembers;
        this.history = history;
    }

    public Team(String name, ArrayList<Player> teamMembers) {//maybe not necessary as new teams can have an empty TeamHistory
        this.name = name;
        this.teamMembers = teamMembers;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getTeamMembers() {
        return teamMembers;
    }

    public TeamHistory getHistory() {
        return history;
    }

    public void setName(String name) {
        this.name = name;
    }
}
