package team;

import domain.match.Match;
import domain.team.Player;
import domain.team.Team;
import domain.tournament.TournamentTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TeamHistoryTest {
    TournamentTeam team1;
    TournamentTeam team2;
    ArrayList<Player> players;
    ArrayList<Player> players2;
    TournamentTeam[] teams;
    Match match;

    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        players2 = new ArrayList<>();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("player2");
        players.add(player1);
        team1 = new TournamentTeam(new Team("Team 1", players));
        team2 = new TournamentTeam(new Team("Team 2", players2));
        TournamentTeam[] teams = {team1, team2};
        Date date = new Date();
        match = new Match(teams, date);
        match.setWinner(team1);
    }

    @Test
    void addMatch() {
        team1.getTeam().getHistory().addMatch(match);
        assertEquals(team1.getTeam().getHistory().getTeamStreak(),match);

    }

    @Test
    void getTeamStreak() {

    }

    @Test
    void addTournamentHistoryResults() {
    }

    @Test
    void testToString() {
    }
}