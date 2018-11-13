import app.entities.CreatureCard;
import app.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


class PlayerTest {

    List<CreatureCard> deck;
    Player player;

    @Mock
    CreatureCard card;

    @BeforeEach
    void setUp() {
        deck = getDeck(20);
        player = new Player(deck, "test");
    }

    List<CreatureCard> getDeck(int deckSize) {
        List<CreatureCard> deck = new ArrayList();
        for (int i = 0; i < deckSize; i++) {
            card = new CreatureCard("Card " + 1);
            deck.add(card);
        }
        return deck;
    }

    @Test
    void reduceHp() {
        int hpBefore = player.getHp();
        final int DAMAGE = 5;

        player.reduceHp(DAMAGE);
        int hpAfter = player.getHp();

        assertEquals(hpAfter, hpBefore - DAMAGE);
    }

    @Test
    void reduceHpWithMoreThanPlayerHave() {
        int hpBefore = player.getHp();
        final int OVERKILL = hpBefore + 1;

        player.reduceHp(OVERKILL);
        int hpAfter = player.getHp();

        assertEquals(hpAfter, hpBefore - OVERKILL);
    }

    @Test
    void increaseHp() {
        int hpBefore = player.getHp();
        final int HEAL = 10;

        player.increaseHp(HEAL);
        int hpAfter = player.getHp();

        assertEquals(hpAfter - 10, hpBefore);
    }

    @Test
    void drawCard() {
        deck = getDeck(10);
        Player player = new Player(deck, "test");
        player.drawCard();
        assertEquals(6, player.getCardsOnHand().size());
        assertEquals(4, player.getCardsInDeck().size());
    }

    @Test
    void playCard() throws Exception {

        deck = getDeck(5);
        player.setCardsOnHand(deck);

        assertFalse(player.isHasPlayedCard());
        player.playCard(1);
        assertTrue(player.isHasPlayedCard());

        assertEquals(4, player.getCardsOnHand().size());
        assertEquals(1, player.getCardsOnTable().size());
    }


    @Test
    void removeCardIfDead() {
        deck = getDeck(5);
        player.setCardsOnTable(deck);

        player.removeCardIfDead();
        assertEquals(5, player.getCardsOnTable().size());
        assertTrue(player.getGraveYard().size() <= 0);

        deck.get(3).decreaseHp(4);

        player.removeCardIfDead();
        assertEquals(4, player.getCardsOnTable().size());
        assertEquals(1, player.getGraveYard().size());
    }

    @Test
    void regenerateEnergy() {
        player.regenerateEnergy(2);
        assertEquals(12, player.getEnergyPoints());
        player.regenerateEnergy(10);
        assertEquals(22, player.getEnergyPoints());
        player.regenerateEnergy(-10);
        assertEquals(12, player.getEnergyPoints());
    }

}