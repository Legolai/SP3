package domain.tournament;

import domain.team.Team;
import domain.match.MatchProgram;
import java.util.ArrayList;

public abstract class Tournament {
    protected String name;
    protected Sport tournamentType;
    protected ArrayList<Team> contenders;
    protected MatchProgram program;
    protected ArrayList<TournamentHistory> history;


    public Tournament(String name, Sport tournamentType) {      //is the rest needed immediately?
        this.name = name;
        this.tournamentType = tournamentType;
        contenders = new ArrayList<>();
        program = new MatchProgram();
        history = new ArrayList<>();
    }
    public Tournament(String name, Sport tournamentType, ArrayList<Team> contenders) {
        this.name = name;
        this.tournamentType = tournamentType;
        this.contenders = contenders;
        program = new MatchProgram();
        history = new ArrayList<>();
    }
    public Tournament(String name, Sport tournamentType, ArrayList<Team> contenders,
                      MatchProgram program, ArrayList<TournamentHistory> history) {
        this.name = name;
        this.tournamentType = tournamentType;
        this.contenders = contenders;
        this.program = program;
        this.history = history;
    }

    public abstract void createMatchProgram(String matchType);
    public abstract MatchProgram getMatchProgram();
    public abstract String viewTeamRankings();
    public abstract void viewGroupRankings();
    public abstract void viewTeams();
}
