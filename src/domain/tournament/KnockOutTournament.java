package domain.tournament;

import domain.match.Match;

import java.util.ArrayList;

public class KnockOutTournament extends Tournament {

    public KnockOutTournament(String name, Sport tournamentType) {
        super(name, tournamentType);
    }

    public KnockOutTournament(String name, Sport tournamentType, ArrayList<TournamentTeam> contenders) {
        super(name, tournamentType, contenders);
    }

    public KnockOutTournament(int id, String name, Sport tournamentType) {
        super(id, name, tournamentType);
    }

    public KnockOutTournament(int id, String name, Sport tournamentType, ArrayList<TournamentTeam> contenders) {
        super(id, name, tournamentType, contenders);
    }

    public KnockOutTournament(int id, String name, Sport tournamentType, ArrayList<TournamentTeam> contenders, ArrayList<Match> matches) {
        super(id, name, tournamentType, contenders);
        program.createMatchProgram(matches);
    }


    @Override
    public void createMatchProgram(String matchType) {
        program.setMatchType("score");      // for now, it is score
        program.createMatchProgram(this.name);
    }


    @Override
    public String viewTeamRankings() {
        return null;
    }

}
