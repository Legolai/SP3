package team;

import domain.team.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player1;

    @BeforeEach
    void setUp() {
        player1 = new Player("Michael");
    }

    @Test
    void getName() {
        assertEquals("Michael", player1.getName());
    }

    @Test
    void setName() {
        player1.setName("Nicolai");
        assertEquals("Nicolai", player1.getName());
    }

    @Test
    void testToString() {
        assertEquals("Michael", player1.toString());
    }
}