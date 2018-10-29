package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import app.controllers.Game;
import app.entities.GameCard;
import java.util.ArrayList;
import java.util.List;

//@ExtendWith(MockitoExtension.class)
class GameTest {

    List<GameCard> deck;
    Game game = new Game();

//    @BeforeEach
//    void mockedDeck() {
//        deck = new ArrayList<>();
//        for(int i = 0; i < 10; i++) {
//            GameCard card = new GameCard(i + 1 + "");
//
//            deck.add(card);
//        }
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

        game.print("test");
        game.getUserInput();
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