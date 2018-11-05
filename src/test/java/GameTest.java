import app.entities.GameCard;
import app.entities.Player;
import app.gui.Print;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.controllers.Game;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class GameTest {


    private Game game;
    private final int DECK_SIZE = 20;
    @Mock
    private Player player1;
    @Mock
    private Player player2;


    @BeforeEach
    void setUpDeck() {
        game = new Game(getDeck(20));
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
    void attack() {
/*        List<GameCard> attackingCards = getDeck(3);
        List<GameCard> defendingCards = getDeck(3);
        GameCard attackingCard = attackingCards.get(0);
        GameCard defendingCard = defendingCards.get(1);
        int hpAttackingCard = attackingCard.getHp();
        int hpDefendingCard = defendingCard.getHp();
        game.attack(attackingCard, defendingCard);
        assertTrue(attackingCard.getIsUsed() == true);
        assertTrue(attackingCard.getHp() < hpAttackingCard || defendingCard.getHp() < hpDefendingCard);
        verify(player1, times(1)).getIsCardDead("0");
        verify(player2, times(1)).getIsCardDead("1");*/
    }



    @Test
    void attackPlayer() {

        int attackNumber = game.randomNumber(5);
        int hpAfterAttack = game.getPlayer1().getHp() - attackNumber;
        game.attackPlayer(game.getPlayer1(), attackNumber);
        assertEquals(game.getPlayer1().getHp(), hpAfterAttack);
    }

    @Test
    void attackPlayerWhenHpIsBellow0() {

        int attackNumber = 10;
        int hpAfterAttack = game.getPlayer1().getHp() - attackNumber;
        game.attackPlayer(game.getPlayer1(), attackNumber);
        assertEquals(0, hpAfterAttack);
        verify(game, times(1)).isPlayerDead(game.getPlayer1());

    }


    @Test
    void randomNumber() {
        int result = game.randomNumber(10);
        assertTrue(result > 0 && result <= 10);
    }
}