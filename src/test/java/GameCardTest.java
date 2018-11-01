import app.entities.GameCard;
import app.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameCardTest {

    GameCard card;

    @BeforeEach
    void setUp() {
        card = new GameCard("card1");
    }

    @Test
    void increaseHp() {
        int hpBefore = card.getHp();
        final int HEAL = 10;

        card.increaseHp(HEAL);
        int hpAfter = card.getHp();

        assertEquals(hpAfter - 10, hpBefore);
    }

    @Test
    void decreaseHp() {
        int hpBefore = card.getHp();
        final int DAMAGE = 5;

        card.decreaseHp(DAMAGE);
        int hpAfter = card.getHp();

        assertEquals(hpAfter, hpBefore-DAMAGE);
    }

    @Test
    void getHp() {
    }

    @Test
    void setIsUsed() {
    }

    @Test
    void getIsUsed() {
    }
}