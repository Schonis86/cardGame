package test;

import app.controllers.Game;
import app.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.smartcardio.Card;

import java.util.List;

import static org.mockito.Mockito.spy;

class PlayerTest {

    List<Card> exampleCards;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getStartCards() {
        Player playerSpy = spy(Player.class);
        playerSpy.setCardsInDeck();


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