import app.controllers.HighScore;
import app.entities.CreatureCard;
import app.entities.GameCard;
import app.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import app.controllers.Game;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    void setUpDeck() throws IOException {
        game = new Game(getDeck(20));
    }

    List<GameCard> getDeck(int deckSize) {
        List<GameCard> deck = new ArrayList();
        for (int i = 0; i < deckSize; i++) {
            CreatureCard card = new CreatureCard(10, "Ali", 5, 2, 2, 2, "FIRE", false, "bild");
            deck.add(card);
        }
        return deck;
    }

    @Test
    void divideCards() throws IOException {
        List<GameCard> deck = getDeck(DECK_SIZE);
        game = new Game(deck);

        game.divideCards();
        int deckSizePlayer1 = game.getPlayer1Cards().size();
        int deckSizePlayer2 = game.getPlayer2Cards().size();
        assertEquals(DECK_SIZE / 2, deckSizePlayer1);
        assertEquals(DECK_SIZE / 2, deckSizePlayer2);
    }


    @Test
    void attackCard() throws Exception {
        List<GameCard> attackingCards = getDeck(3);
        List<GameCard> defendingCards = getDeck(3);
        CreatureCard attackingCard = (CreatureCard) attackingCards.get(0);
        CreatureCard defendingCard = (CreatureCard) defendingCards.get(1);
        int hpAttackingCard = attackingCard.getHp();
        int hpDefendingCard = defendingCard.getHp();
        game.setRoundCounter(2);
        game.attackCard(attackingCard, defendingCard);
        assertTrue(attackingCard.getIsUsed() == true);
        assertTrue(attackingCard.getHp() < hpAttackingCard || defendingCard.getHp() < hpDefendingCard);
    }




    @Test
    void isPlayerOutOfCards(){
        Player player = new Player(getDeck(6), "Lina");
        List<GameCard> tempCardDeck = player.getCardsInDeck();
        List<GameCard> tempCardHand = player.getCardsOnHand();
        assertFalse(game.isPlayerOutOfCards(player));
        List<CreatureCard> tempCardsOnTableWithCards = Arrays.asList(new CreatureCard(10,"Lina",1,1,2,9, "FIRE",false,"bild"));
        List<CreatureCard> tempCardsOnTableEmpty = Arrays.asList();
        player.setCardsOnTable(tempCardsOnTableWithCards); // 1 5 1
        tempCardDeck.clear();
        player.setCardsInDeck(tempCardDeck);                        // 0 5 1
        assertFalse(game.isPlayerOutOfCards(player));
        player.setCardsOnHand(tempCardDeck);                        // 0 0 1
        assertFalse(game.isPlayerOutOfCards(player));
        player.setCardsOnTable(tempCardsOnTableEmpty);                       //0 0 0
        assertTrue(game.isPlayerOutOfCards(player));
    }

    @Test
    void isPlayerDead() {
        Player player = new Player(getDeck(DECK_SIZE), "test");
        player.setHp(-1);
        assertTrue(game.isPlayerDead(player));

        player.setHp(10);
        assertFalse(game.isPlayerDead(player));

    }



    @Test
    void attackPlayer() throws Exception {
        game.setRoundCounter(2);
        int attackNumber = game.randomNumber(5);
        int hpAfterAttack = game.getPlayer1().getHp() - attackNumber;
        game.attackPlayer(game.getPlayer1(), attackNumber);
        assertEquals(game.getPlayer1().getHp(), hpAfterAttack);
    }

    @Test
    void attackPlayerWhenHpIsBellow0() throws Exception {
        game.setRoundCounter(2);
        int attackNumber = 10;
        System.out.println(game.getPlayer1().getHp());
        int hpAfterAttack = game.getPlayer1().getHp() - attackNumber;
        System.out.println(hpAfterAttack);
        game.attackPlayer(game.getPlayer1(), attackNumber);

        assertEquals(hpAfterAttack, game.getPlayer1().getHp() );


    }


    @Test
    void randomNumber() {
        int result = game.randomNumber(10);
        assertTrue(result > 0 && result <= 10);
    }
}