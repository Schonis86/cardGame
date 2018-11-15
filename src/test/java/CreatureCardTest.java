import app.entities.CreatureCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static app.AttackType.FIRE;
import static org.junit.jupiter.api.Assertions.*;

class CreatureCardTest {

    CreatureCard card;

    @BeforeEach
    void setUp() {
        card = new CreatureCard(10 , "Ali", 5, 2, 2, 2, null, false);
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