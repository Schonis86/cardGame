import app.entities.GameCard;
import app.entities.Player;
import app.gui.Print;
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
    @Mock
    private Player player1;
    @Mock
    private Player player2;


    @BeforeEach
    void setUpDeck() {
        game = new Game(mockDeck);
    }

    List<GameCard> getDeck(int deckSize) {
        List<GameCard> deck = new ArrayList();
        for (int i = 0; i < deckSize; i++) {
            GameCard card = new GameCard("kort" + (i + 1));
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
        assertEquals(DECK_SIZE / 2, deckSizePlayer1);
        assertEquals(DECK_SIZE / 2, deckSizePlayer2);
    }

    @Test
    void toggleTurn() {

        List<GameCard> deck = getDeck(0);
        Game gameSpy = spy(new Game(deck));

        assertTrue(gameSpy.isPlayer1Turn());
        gameSpy.toggleTurn();
        assertFalse(gameSpy.isPlayer1Turn());
  //      verify(gameSpy, times(1)).print("Player 2 turn");

        gameSpy.toggleTurn();
        assertTrue(gameSpy.isPlayer1Turn());
    //    verify(gameSpy, times(1)).print("Player 1 turn");

        int resultTurnCounter = gameSpy.getTurnCounter();
        assertEquals(2, resultTurnCounter);

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
        List<GameCard> attackingCards = getDeck(3);
        List<GameCard> defendingCards = getDeck(3);
        GameCard attackingCard = attackingCards.get(0);
        GameCard defendingCard = defendingCards.get(1);
        int hpAttackingCard = attackingCard.getHp();
        int hpDefendingCard = defendingCard.getHp();
        game.attack(attackingCard, defendingCard);
        assertTrue(attackingCard.getIsUsed() == true);
        assertTrue(attackingCard.getHp() < hpAttackingCard || defendingCard.getHp() < hpDefendingCard);
        verify(player1, times(1)).getIsCardDead("0");
        verify(player2, times(1)).getIsCardDead("1");
    }

    ;


    @Test
    void attackPlayer() {
        Game game = spy(new Game(mockDeck));
        Player player = new Player(mockDeck,"test");

        int attackNumber = game.randomNumber(5);
        int hpAfterAttack = player.getHp() - attackNumber;
        game.attackPlayer(player, attackNumber);
        assertEquals(player.getHp(), hpAfterAttack);
        verify(game, times(1)).isPlayerDead(player);


    }

    @Test
    void attackPlayerWhenHpIsBellow0() {
        Game game = spy(new Game(mockDeck));
        Player player = new Player(mockDeck,"test");

        int attackNumber = 10;
        int hpAfterAttack = player.getHp() - attackNumber;
        game.attackPlayer(player, attackNumber);
        assertEquals(0, hpAfterAttack);
        verify(game, times(1)).isPlayerDead(player);
    //    verify(game, times(1)).killPlayer(player);
    }

    @Test
    void getUserInput() {
        //
    }

/*    @Test
    void isPlayerDead() {
        Player player = mock(Player.class);
        when(player.getHp()).thenReturn(1);
        assertFalse(game.isPlayerDead(player));

        when(player.getHp()).thenReturn(0);
        assertTrue(game.isPlayerDead(player));

        player = null;
        assertTrue(game.isPlayerDead(player));
    }*/

    @Test
    void roundCheck() {
        assertEquals(0, game.getRoundCounter());
        game.toggleTurn();
        assertEquals(1, game.getRoundCounter());
        game.toggleTurn();
        assertEquals(1, game.getRoundCounter());
        game.toggleTurn();
        assertEquals(2, game.getRoundCounter());
        game.toggleTurn();
        assertEquals(2, game.getRoundCounter());
        game.toggleTurn();
        assertEquals(3, game.getRoundCounter());
        game.toggleTurn();
        assertEquals(3, game.getRoundCounter());
        game.toggleTurn();
        assertEquals(4, game.getRoundCounter());
        assertEquals(7, game.getTurnCounter());
        assertEquals(7, game.getTurnCounter());
        assertTrue(game.getRoundCounter() * 2 == game.getTurnCounter() || game.getRoundCounter() * 2 - 1 == game.getTurnCounter());
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