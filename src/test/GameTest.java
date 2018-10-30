package test;


import app.entities.GameCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.controllers.Game;

import java.util.ArrayList;
import java.util.List;

//@ExtendWith(MockitoExtension.class)
class GameTest {

    //    @Mock
//    Game mockedGame;
    Game game;
    List<GameCard> deck;
    final int DECK_SIZE = 20;
//
//    @BeforeAll
//    static void setUp() {
//        game = new Game();
//    }

    @BeforeEach
    void setUp() {
        deck = new ArrayList<>();
        for(int i = 0; i < DECK_SIZE; i++) {
            GameCard card = new GameCard("kort" + (i+1));
            deck.add(card);
        }
        game = new Game(deck);
    }

    @Test
    void devideCards() {
        game.devideCards();
        int deckSizePlayer1 = game.getPlayer1Cards().size();
        int deckSizePlayer2 = game.getPlayer2Cards().size();
        assertEquals(DECK_SIZE/2, deckSizePlayer1);
        assertEquals(DECK_SIZE/2, deckSizePlayer2);
    }

//    @Test
//    void

    @Test
    void toggleTurn() {

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