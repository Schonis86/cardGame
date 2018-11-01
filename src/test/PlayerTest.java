package test;

import app.entities.GameCard;
import app.entities.Player;
import javafx.beans.binding.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    List<GameCard> deck;
    Player player;

    @Mock
    GameCard card;

    @BeforeEach
    void setUp() {
        deck = getDeck(20);
        player = new Player(deck);
    }

    List<GameCard> getDeck(int deckSize) {
        List<GameCard> deck = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
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

        assertEquals(hpAfter, hpBefore-DAMAGE);
    }

    @Test
    void reduceHpWithMoreThanPlayerHave() {
        int hpBefore = player.getHp();
        final int OVERKILL = hpBefore + 1;

        player.reduceHp(OVERKILL);
        int hpAfter = player.getHp();

        assertEquals(hpAfter, hpBefore-OVERKILL);
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
        Player player = new Player(deck);
        player.drawCard();
        assertEquals(1, player.getCardsOnHand().size());
        assertEquals(9, player.getCardsInDeck().size());
    }

    @Test
    void playCard() {

        deck = getDeck(5);
        player.setCardsOnHand(deck);

        assertFalse(player.isHasPlayedCard());
        player.playCard( 1 );
        assertTrue(player.isHasPlayedCard());

        assertEquals(4, player.getCardsOnHand().size());
        assertEquals(1, player.getCardsOnTable().size());

    }


    @Test
    void killCard() {
        List<GameCard> cardsOnTable = new ArrayList<>();
        GameCard card1 = new GameCard("card 1");
        GameCard card2 = new GameCard("card 2");
        cardsOnTable.add(card1);
        cardsOnTable.add(card2);
        card2.setHp(5);

        Player player = new Player(deck);
        player.setCardsOnTable(cardsOnTable);

        player.killCard("card 2");
        assertEquals(2, player.getCardsOnTable().size() );
        assertTrue(cardsOnTable.contains(card2));
        assertEquals(5, card2.getHp());

        card2.setHp(0);
        player.killCard("card 2");
        assertEquals(1, player.getCardsOnTable().size() );
        assertFalse(cardsOnTable.contains(card2));
        assertEquals(0, card2.getHp());

    }
}