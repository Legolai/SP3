package domain.tournament;

import java.util.ArrayList;

public class GroupTournament extends Tournament {
    private final ArrayList<Group> groups;
    private final KnockOutTournament finals;


    public GroupTournament(String name, Sport tournamentType, ArrayList<TournamentTeam> contenders) {
        super(name, tournamentType, contenders);
        groups = new ArrayList<>();
        finals = new KnockOutTournament(name, tournamentType);   //new method to add contenders later on
    }

    @Override
    public void createMatchProgram(String matchType) {
        program.createMatchProgram(this.name);
    }

    @Override
    public String viewTeamRankings() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
