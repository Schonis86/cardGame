package test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.controllers.Game;

//@ExtendWith(MockitoExtension.class)
class GameTest {

    //    @Mock
//    Game mockedGame;
    Game game;
//
//    @BeforeAll
//    static void setUp() {
//        game = new Game();
//    }

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void devideCards() {
//        game.devideCards();
//        assertEquals(10, game.getPlayer1Cards());
    }

    @Test
    void toggleTurn() {

        Game gameSpy = spy(Game.class);
        gameSpy.toggleTurn();
        verify(gameSpy, times(1)).print("Player 2 turn");
        gameSpy.toggleTurn();
        verify(gameSpy, times(1)).print("Player 1 turn");

        int resultTurnCounter = gameSpy.getTurnCounter();
        assertEquals(2,resultTurnCounter);

        verify(gameSpy, times(2)).getUserInput();

        //// SÅHÄR KAN VI ANVÄNDA SPY/MOCK I DET METODER VI BEHÖVER OCH FORTSÄTTA ATT ANVÄNDA BARA VANLIG JUNIT I RESTEN!


  /*      assertEquals(0, game.getTurnCounter());
        assertTrue(game.isPlayer1Turn());
        game.toggleTurn();
        assertEquals(1, game.getTurnCounter());
        assertFalse(game.isPlayer1Turn());
        game.toggleTurn();
        assertEquals(2, game.getTurnCounter());
        assertTrue(game.isPlayer1Turn());*/

        //  verify(gameSpy, times(1)).getUserInput();


/*       verify(mockedGame, times(2)).print("test");
        verify(mockedGame, times(2)).getUserInput();*/
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