package test;

import app.entities.GameCard;
import app.entities.Player;
import javafx.beans.binding.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PlayerTest {

   List <GameCard> deck;
   Player player;

    @Mock
    GameCard card;

    @BeforeEach
    void setUp() {
        deck = getDeck(20);
        player = new Player(deck);
    }

    List<GameCard> getDeck(int deckSize) {
        List<GameCard> deck = new ArrayList<>();
        for (int i = 0; i < deckSize; i++) {
            deck.add(card);
        }
        return deck;
    }

    @Test
    void reduceHp() {
        int hp1 = player.getHp();
        final int DAMAGE = 5;

        player.reduceHp(DAMAGE);
        int hp2 = player.getHp();

        assertEquals(hp2, hp1-DAMAGE);
    }

    @Test
    void reduceHpWithMoreThanPlayerHave() {
        int hp1 = player.getHp();
        final int OVERKILL = 20;

        player.reduceHp(OVERKILL);
        int hp2 = player.getHp();

        assertEquals(hp2, hp1-OVERKILL);
    }

    @Test
    void drawCard() {
        deck = getDeck(10);
        Player player = new Player(deck);
        player.drawCard();
        assertEquals(1, player.getCardsOnHand().size());
        assertEquals(9, player.getCardsInDeck().size());

    }

    @Test
    void playCard() {

        deck = getDeck(5);
        player.setCardsOnHand(deck);

        player.playCard( 1 );
        player.setHasPlayedCard( true );

        assertEquals(4, player.getCardsOnHand().size());
        assertEquals(2, player.getCardsOnTable().size());

        //testa så att man inte kan spela > 1 kort samtidigt
    }


}