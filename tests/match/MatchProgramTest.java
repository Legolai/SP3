package match;

import domain.team.Player;
import domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MatchProgramTest {
    Player player1;
    Player player2;
    Player player3;
    Team team6;

    Player player4;
    Player player5;
    Team team2;


    @BeforeEach
    void setUp() {
        player1 = new Player(1,"Michael");
        player2 = new Player(2,"Nicolai");
        player3 = new Player(3,"Oscar");
        ArrayList<Player> a = new ArrayList<>();
        a.add(player1);
        a.add(player2);
        a.add(player3);
        team6 = new Team("Hold 6",a);

        ArrayList<Player> b = new ArrayList<>();
        player4 = new Player(4,"Jesper");
        player5 = new Player(5,"Tess");
        team2 = new Team("Hold 2", b);
        team2.addMember(player4);
        team2.addMember(player5);

        TemporaryTeamImporter test = new TemporaryTeamImporter();
        test.readFile();
        System.out.println(test.teams.get(0).toString());

    }

    @Test
    void createMatchProgram() {
    }

    @Test
    void changeMatchContenders() {
    }

    @Test
    void getMatchByID() {
    }

    @Test
    void testToString() {
    }
}