package domain.tournament;

import domain.team.Team;
import domain.match.MatchProgram;
import java.util.ArrayList;

public class GroupTournament extends Tournament{
    private ArrayList<Group> groups;
    private KnockOutTournament finals;


    public GroupTournament(String name, Sport tournamentType, ArrayList<Team> contenders) {
        super(name, tournamentType, contenders);
        groups = new ArrayList<>();
        finals = new KnockOutTournament(name,tournamentType);   //new method to add contenders later on
    }

    @Override
    public void createMatchProgram(String matchType) {

    }

    @Override
    public MatchProgram getMatchProgram() {
        return null;
    }

    @Override
    public String viewTeamRankings() {
        return null;
    }

    @Override
    public void viewGroupRankings() {

    }

    @Override
    public void viewTeams() {

    }

    @Override
    public String toString() {
        return null;
    }
}
