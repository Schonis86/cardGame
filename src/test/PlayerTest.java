package test;

import app.controllers.Game;
import app.entities.GameCard;
import app.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

class PlayerTest {

    List<GameCard> exampleList;

    @Mock
    GameCard card;

    @BeforeEach
    void setUp() {
        exampleList = new ArrayList<>();
    }

    List<GameCard> getDeck(int deckSize) {
        List<GameCard> deck = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            deck.add(card);
        }
        return deck;
    }

    @Test
    void getStartCards() {

    }

    @Test
    void getHp() {
    }

    @Test
    void setHp() {
    }

    @Test
    void getCardsOnHand() {
    }

    @Test
    void setCardsOnHand() {
    }

    @Test
    void getCardsInDeck() {
    }

    @Test
    void setCardsInDeck() {
    }

    @Test
    void getCardsOnTable() {
    }

    @Test
    void setCardsOnTable() {
    }

    @Test
    void isHasPlayedCard() {
    }

    @Test
    void setHasPlayedCard() {
    }

    @Test
    void drawCard() {
        exampleList = getDeck(10);
        Player player = new Player(exampleList);
        player.drawCard();
        assertEquals(1, player.getCardsOnHand().size());
        assertEquals(9, player.getCardsInDeck().size());

    }

    @Test
    void playCard() {
    }

    @Test
    void killCard() {
    }

    @Test
    void reduceHp() {
    }

    @Test
    void increaseHp() {
    }
}