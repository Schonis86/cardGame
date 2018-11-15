import app.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
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
        monsterCard = new CreatureCard(9, "Ali", 5, 2, 2, 2, "FIRE", false);
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
        monsterCard.setHp(5);
        int currentCreatureCardHp = monsterCard.getHp();
        int hpMax = monsterCard.gethpMax();

        List<CreatureCard> tempList = new ArrayList<>();
        tempList.add(monsterCard);

        magic.healFriendlyCards( tempList, 2 );
        int cardHpIncreased = tempList.get(0).getHp();

        System.out.println(cardHpIncreased);

        assertFalse(cardHpIncreased <= currentCreatureCardHp );
        assertTrue( cardHpIncreased > currentCreatureCardHp || cardHpIncreased == hpMax );
    }

    //ej klar
    @Test
    void healFriendlyCardsOverHeal() {

        int currentCreatureCardHp = monsterCard.getHp();
        int hpMax = monsterCard.gethpMax();

        List<CreatureCard> tempList = new ArrayList<>();
        tempList.add(monsterCard);

        magic.healOneCard( monsterCard, 2);
        int creatureCardHpIncreased = tempList.get(0).getHp();

        System.out.println(creatureCardHpIncreased);

        assertTrue( creatureCardHpIncreased == currentCreatureCardHp || creatureCardHpIncreased == hpMax);


    }

    @Test
    void damageEnemyCards() {
        int currentCardsHp = monsterCard.getHp();
        List<CreatureCard> tempList = new ArrayList<>();
        tempList.add(monsterCard);
        magic.damageEnemyCards( tempList, 2 );
        int cardsHpDecreased = tempList.get(0).getHp();
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

    @Test
    void healOneCard() {
        monsterCard.setHp(5);
        int currentCreatureCardHp = monsterCard.getHp();
        int hpMax = monsterCard.gethpMax();

        magic.healOneCard( monsterCard, 2 );
        int creatureCardHpIncreased = monsterCard.getHp();

        System.out.println(creatureCardHpIncreased);
        System.out.println(currentCreatureCardHp);

        assertTrue( creatureCardHpIncreased > currentCreatureCardHp || currentCreatureCardHp == hpMax );
    }

    @Test
    void healOneCardOverHeal() {
        int currentCreatureCardHp = monsterCard.getHp();
        int hpMax = monsterCard.gethpMax();

        magic.healOneCard( monsterCard, 2);
        int creatureCardHpIncreased = monsterCard.getHp();

        System.out.println(creatureCardHpIncreased);

        assertTrue( creatureCardHpIncreased <= currentCreatureCardHp || creatureCardHpIncreased >= hpMax );
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