package domain.team;

import java.util.ArrayList;

public class Team {
    private int ID;
    private String name;
    private ArrayList<Player> teamMembers;
    private TeamHistory history;


    public Team(int ID, String name, ArrayList<Player> teamMembers) {
        this.ID = ID;
        this.name = name;
        this.teamMembers = teamMembers;
        history = new TeamHistory(this);
    }

    public Team(String name, ArrayList<Player> teamMembers) {
        this.name = name;
        this.teamMembers = teamMembers;
        history = new TeamHistory(this);
    }

    public Team(String name, ArrayList<Player> teamMembers, TeamHistory history) {
        this.name = name;
        this.teamMembers = teamMembers;
        this.history = history;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Player> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(ArrayList<Player> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public void addMember(Player a) {
        teamMembers.add(a);
    }

    public void removeMember(Player a) {
        teamMembers.remove(a);
    }

    public TeamHistory getHistory() {
        return history;
    }

    public void setHistory(TeamHistory history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Team: " + name + ", Players: " + teamMembers.toString() + ", " + history.getTeamStreak();
    }

    public int getID() {
        return this.ID;
    }
}
