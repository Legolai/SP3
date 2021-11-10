package domain.tournament;

import domain.team.Team;
import domain.match.MatchProgram;
import java.util.ArrayList;

public class KnockOutTournament extends Tournament{

    public KnockOutTournament(String name, Sport tournamentType) {
        super(name, tournamentType);
    }
    public KnockOutTournament(String name, Sport tournamentType, ArrayList<TournamentTeam> contenders) {
        super(name, tournamentType, contenders);
    }

    @Override
    public void createMatchProgram(String matchType) {
        program.setMatchType("score");      // for now it is score
        program.createMatchProgram();
    }



    @Override
    public String viewTeamRankings() {
        return null;
    }

}
