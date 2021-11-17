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

    public KnockOutTournament(int id, String name, Sport tournamentType,  ArrayList<TournamentTeam> contenders)  {      //DB consturctor with no macth_games
        super(id, name, tournamentType, contenders);
        createMatchProgram("SCORE");
    }

    @Override
    public void createMatchProgram(String matchType) {
        program.setMatchType("score");      // for now it is score
        program.createMatchProgram(this.name);
    }



    @Override
    public String viewTeamRankings() {
        return null;
    }

}
