package team;

import domain.team.Player;
import domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    Player player1;
    Player player2;
    ArrayList<Player> players;
    Team team6;

    @BeforeEach
    void setUp() {
        player1 = new Player(1,"Michael");
        player2 = new Player(2,"Nicolai");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        team6 = new Team("Hold 6", players);

    }

    @Test
    void getName() {
        assertEquals("Hold 6", team6.getName());
    }

    @Test
    void setName() {        // this gives error cuz one is small h other is H
        team6.setName("hold 66");
        assertEquals("Hold 66", team6.getName());
    }

    @Test
    void getTeamMembers() {
        ArrayList<Player> a = new ArrayList<>();
        a.add(player1);
        a.add(player2);
        assertEquals(a, team6.getTeamMembers());     // (wrong): this fails because not set teammembers in setup
    }

    @Test
    void setTeamMembers() {
        ArrayList<Player> b = new ArrayList<>();
        b.add(player2);
        b.add(player1);
        team6.setTeamMembers(b);
        assertEquals(b, team6.getTeamMembers());
    }

    @Test
    void addMember() {
    }

    @Test
    void getHistory() {
    }

    @Test
    void setHistory() {
    }
}