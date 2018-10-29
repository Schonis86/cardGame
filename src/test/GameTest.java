package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import app.controllers.Game;

//@ExtendWith(MockitoExtension.class)
class GameTest {

//    @Mock
//    Game mockedGame;
    Game game = new Game();
//
//    @BeforeAll
//    static void setUp() {
//        game = new Game();
//    }

    @Test
    void devideCards() {
//        game.devideCards();
//        assertEquals(10, game.getPlayer1Cards());
    }

    @Test
    void toggleTurn() {
        assertEquals(0, game.getTurnCounter());
        assertTrue(game.isPlayer1Turn());
        game.toggleTurn();
        assertEquals(1, game.getTurnCounter());
        assertFalse(game.isPlayer1Turn());
        game.toggleTurn();
        assertEquals(2, game.getTurnCounter());
        assertTrue(game.isPlayer1Turn());

//        verify(mockedGame, times(2)).print("test");
//        verify(mockedGame, times(2)).getUserInput();
    }

    @Test
    void print(String message) {

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