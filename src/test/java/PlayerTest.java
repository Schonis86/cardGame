import app.controllers.Game;
import app.entities.CreatureCard;
import app.entities.GameCard;
import app.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PlayerTest {

    List<GameCard> deck;
    Player player;

    @Mock
    CreatureCard card;

    @BeforeEach
    void setUp() {
        deck = getDeck(20);
        player = new Player(deck, "test");
    }

    List<GameCard> getDeck(int deckSize) {
        List<GameCard> deck = new ArrayList();
        for (int i = 0; i < deckSize; i++) {
            card = new CreatureCard(10 , "Ali", 5, 2, 2, 2, "FIRE", false, "bild");
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
        List<CreatureCard> deck2 = (List<CreatureCard>)(List<?>) deck;
        player.setCardsOnTable(deck2);

        player.removeCardIfDead();
        assertEquals(5, player.getCardsOnTable().size());
        assertTrue(player.getGraveYard().size() <= 0);

        deck2.get(3).decreaseHp( deck2.get(3).getHp() );

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