package team;

import domain.match.Match;
import domain.match.MatchByScore;
import domain.team.Player;
import domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeamHistoryTest {
    Team team1;
    Team team2;
    ArrayList<Player> players;
    ArrayList<Player> players2;
    Team[] teams;
    Match match;

    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        players2 = new ArrayList<>();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("player2");
        players.add(player1);
        team1 = new Team("Team 1", players);
        team2 = new Team("Team 2", players2);
        Team[] teams = {team1, team2};
        match = new MatchByScore(teams, null);
        match.setWinner(team1);
    }

    @Test
    void addMatch() {
        team1.getHistory().addMatch(match);
        assertEquals(team1.getHistory().getTeamStreak(),match);

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