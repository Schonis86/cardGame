package test;

import app.controllers.Game;
import app.entities.GameCard;
import app.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

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
    void drawCard() {
        deck = getDeck(10);
        Player player = new Player(deck);
        player.drawCard();
        assertEquals(1, player.getCardsOnHand().size());
        assertEquals(9, player.getCardsInDeck().size());

    }


}