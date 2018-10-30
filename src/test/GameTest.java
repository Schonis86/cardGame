package test;


import app.entities.GameCard;
import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.controllers.Game;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;


class GameTest {

    @Mock
    private List<GameCard> mockDeck;
    private Game game;
    private final int DECK_SIZE = 20;


    @BeforeEach
    void setUpDeck() {
        game = new Game(mockDeck);
    }

    List<GameCard> getDeck(int deckSize) {
        List<GameCard> deck = new ArrayList<>();
        for(int i = 0; i < deckSize; i++) {
            GameCard card = new GameCard("kort" + (i+1));
            deck.add(card);
        }
        return deck;
    }

    @Test
    void divideCards() {
        List<GameCard> deck = getDeck(DECK_SIZE);
        game = new Game(deck);

        game.divideCards();
        int deckSizePlayer1 = game.getPlayer1Cards().size();
        int deckSizePlayer2 = game.getPlayer2Cards().size();
        assertEquals(DECK_SIZE/2, deckSizePlayer1);
        assertEquals(DECK_SIZE/2, deckSizePlayer2);
    }

    @Test
    void toggleTurn() {

        List<GameCard> deck = getDeck(0);
        Game gameSpy = spy(new Game(deck));

        assertTrue(gameSpy.isPlayer1Turn());
        gameSpy.toggleTurn();
        assertFalse(gameSpy.isPlayer1Turn());
        verify(gameSpy, times(1)).print("Player 2 turn");

        gameSpy.toggleTurn();
        assertTrue(gameSpy.isPlayer1Turn());
        verify(gameSpy, times(1)).print("Player 1 turn");

        int resultTurnCounter = gameSpy.getTurnCounter();
        assertEquals(2,resultTurnCounter);

        verify(gameSpy, times(2)).getUserInput();
    }

    @Test
    void print() {

    }

    @Test
    void printWinningMessage() {
    }

    @Test
    void playCard() {



    }

    @Test
    void attack() {
    }

    @Test
    void attackPlayer() {
    }

    @Test
    void getUserInput() {
    }

    @Test
    void checkDeath() {
    }

    @Test
    void roundCheck() {

    }

    @Test
    void killPlayer() {
    }

    @Test
    void randomNumber() {
        int result = game.randomNumber(10);
        assertTrue(result > 0 && result <= 10);
    }
}