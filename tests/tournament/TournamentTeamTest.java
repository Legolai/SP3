package tournament;

import domain.team.Player;
import domain.team.Team;
import domain.tournament.Sport;
import domain.tournament.TournamentTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TournamentTeamTest {
    Player player1;
    Player player2;
    Player player3;
    Team team6;
    TournamentTeam tt6;

    @BeforeEach
    void setUp() {
        player1 = new Player("Michael");
        player2 = new Player("Nicolai");
        player3 = new Player("Oscar");
        ArrayList<Player> a = new ArrayList<>();
        a.add(player1);
        a.add(player2);
        a.add(player3);
        team6 = new Team("Hold 6",a);
        tt6 = new TournamentTeam(team6);
    }

    @Test
    void getTeam() {
        assertEquals(team6,tt6.getTeam());
    }

    @Test
    void getPoint() {
        tt6.addPoint(5);
        assertEquals(5,tt6.getPoint());
    }

    @Test
    void getScore() {
        tt6.addScore(9);
        assertEquals(9,tt6.getScore());
    }
}