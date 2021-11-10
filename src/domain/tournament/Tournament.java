package domain.tournament;

import domain.match.MatchProgram;

import java.util.ArrayList;

public abstract class Tournament {
    protected String name;
    protected Sport tournamentType;
    protected ArrayList<TournamentTeam> contenders;
    protected MatchProgram program;
    protected ArrayList<TournamentHistory> history;


    public Tournament(String name, Sport tournamentType) {      //is the rest needed immediately?
        this.name = name;
        this.tournamentType = tournamentType;
        contenders = new ArrayList<>();
        program = new MatchProgram(contenders);
        history = new ArrayList<>();
    }

    public Tournament(String name, Sport tournamentType, ArrayList<TournamentTeam> contenders) {
        this.name = name;
        this.tournamentType = tournamentType;
        this.contenders = contenders;
        program = new MatchProgram(this.contenders);
        history = new ArrayList<>();
    }

    public Tournament(String name, Sport tournamentType, ArrayList<TournamentTeam> contenders,
                      MatchProgram program, ArrayList<TournamentHistory> history) {
        this.name = name;
        this.tournamentType = tournamentType;
        this.contenders = contenders;
        this.program = program;
        this.history = history;
    }

    public Sport getSport() {
        return tournamentType;
    }

    public ArrayList<TournamentTeam> getContenders() {
        return contenders;
    }

    public MatchProgram getMatchProgram() {
        return program;
    }

    public abstract void createMatchProgram(String matchType);

    public abstract String viewTeamRankings();

    public String getName() {
        return name;
    }

}
