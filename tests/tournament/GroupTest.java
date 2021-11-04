package tournament;

import domain.team.Player;
import domain.team.Team;
import domain.tournament.Group;
import domain.tournament.TournamentTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {
    Player player1;
    Player player2;
    Player player3;
    Team team6;
    TournamentTeam tt6;

    Player player4;
    Player player5;
    Team team2;
    TournamentTeam tt2;

    Group group1;


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

        player4 = new Player("Jesper");
        player5 = new Player("Tess");
        team2 = new Team("Hold 2");
        team2.addMember(player4);
        team2.addMember(player5);
        tt2 = new TournamentTeam(team2);

        group1 = new Group();

    }

    @Test
    void addMember() {
        group1.addMember(tt6);
        group1.addMember(tt2);
        ArrayList<TournamentTeam> a = new ArrayList<>();
        a.add(tt6);
        a.add(tt2);
        assertEquals(a,group1.getMembers());
    }

    @Test
    void setMembers() {
        ArrayList<TournamentTeam> a = new ArrayList<>();
        a.add(tt6);
        a.add(tt2);
        group1.setMembers(a);
        assertEquals(a,group1.getMembers());
    }

    @Test
    void getMembers() {
    }       // this already tested in above 2

    @Test
    void sortRanking() {
    }
}