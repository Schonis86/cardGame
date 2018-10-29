package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import app.controllers.Game;
import app.entities.GameCard;
import java.util.ArrayList;
import java.util.List;

//@ExtendWith(MockitoExtension.class)
class GameTest {

    static List<GameCard> deck;
    Game game = new Game();

    @BeforeAll
    static void mockedDeck() {
        deck = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            GameCard card = new GameCard(i + 1 + "");

            deck.add(card);
        }
    }

    @Test
    void devideCards() {
//        game.devideCards();
//        assertEquals(10, game.getPlayer1Cards());
    }

    @Test
    void toggleTurn() {
        assertTrue(game.isPlayer1Turn());
        game.toggleTurn();
        assertFalse(game.isPlayer1Turn());
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
    }
}