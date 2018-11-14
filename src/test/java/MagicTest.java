import app.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static app.AttackType.FIRE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MagicTest {

    private MagicCard magicCard; // <-- not needed here ?
    private Magic magic;
    private CreatureCard monsterCard;
    private Player player;
    List<CreatureCard> cardsOnTable;

    @Mock
    private List<GameCard> mockDeck;

    @BeforeEach
    void setUp() {
        magicCard = new MagicCard("DarkHole", 2, 2); // <-- not needed here ?
        magic = new Magic();
        monsterCard = new CreatureCard(10, "Ali", 5, 2, 2, 2, false);
        player = new Player(mockDeck, "player1");
        cardsOnTable = new ArrayList();
    }

    //ej klar
    @Test
    void selfHealPlayer() {
        int currentPlayerHp = player.getHp();
        magic.selfHealPlayer( player, 2 );
        int hpIncreased = player.getHp();
        assertEquals(currentPlayerHp + 2, hpIncreased);
    }

    //ej klar
    @Test
    void damageEnemyPlayer() {
        int currentPlayerHp = player.getHp();
        magic.damageEnemyPlayer( player, 2 );
        int hpDecreased = player.getHp();
        assertEquals(currentPlayerHp - 2, hpDecreased);
    }

    //ej klar
    @Test
    void healFriendlyCards() {
        int currentCardHp = monsterCard.getHp();
        List<CreatureCard> tempList = new ArrayList<>();
        tempList.add(monsterCard);
        magic.healFriendlyCards( tempList, 2 );
        int cardHpIncreased = tempList.get(0).getHp();
        assertFalse(cardHpIncreased <= currentCardHp );
        assertTrue( cardHpIncreased > currentCardHp );
    }

    @Test
    void damageEnemyCards() {
        int currentCardsHp = monsterCard.getHp();
        List<CreatureCard> tempList = new ArrayList<>();
        tempList.add(monsterCard);
        magic.damageEnemyCards( tempList, 2 );
        int cardsHpDecreased = monsterCard.getHp();
        assertFalse( cardsHpDecreased >= currentCardsHp );
        assertTrue( cardsHpDecreased < currentCardsHp );
    }

    @Test
    void damageEnemyCardsOverKill() {
        monsterCard.setHp(0);
        List<CreatureCard> tempList = new ArrayList<>();
        tempList.add(monsterCard);
        magic.damageEnemyCards( tempList, 10 );
        int cardsHpDecreased = tempList.get(0).getHp();

        System.out.println(cardsHpDecreased);

        assertTrue( cardsHpDecreased >= 0 );
    }

    //ej klar
    @Test
    void healOneCard() {
        int currentCreatureCardHp = monsterCard.getHp();
        magic.healOneCard( monsterCard, 2 );
        int creatureCardHpIncreased = monsterCard.getHp();
//        assertEquals(currentCreatureCardHp + 2, creatureCardHpIncreased);
        assertTrue(currentCreatureCardHp <= creatureCardHpIncreased );
    }

    //ej klar
    @Test
    void damageOneCard() {
        int currentCreatureCardHp = monsterCard.getHp();
        magic.damageOneCard( monsterCard, 2 );
        int creatureCardHpDecreased = monsterCard.getHp();
        assertEquals(currentCreatureCardHp - 2, creatureCardHpDecreased);
    }
}