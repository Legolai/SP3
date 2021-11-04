import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SportTest {
    Sport sport;
    Sport sport2;
    ArrayList<String> aR;

    @BeforeEach
    void setUp() {
        sport = new Sport("Bordfodbold");

        aR = new ArrayList<>();
        aR.add("5 glass");
        aR.add("1 pong bold");
        sport2 = new Sport("Beerpong",aR);
    }

    @Test
    void addRule() {
        String a = "10 maal til at vinde";
        sport.addRule(a);
        assertEquals(sport.getRules().get(0),a);
    }

    @Test
    void getRules() {
        assertEquals(sport2.getRules(),aR);
    }
}