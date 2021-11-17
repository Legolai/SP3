package database;

import domain.team.Team;
import domain.tournament.Tournament;

import java.util.HashMap;


public interface IO {
    HashMap<String, Tournament> loadTournaments();
    void saveTournaments(HashMap<String, Tournament> tournaments);
    HashMap<String, Team> loadTeams();
    void saveTeams(HashMap<String, Team> teams);
}
