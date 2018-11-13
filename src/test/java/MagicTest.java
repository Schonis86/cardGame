import app.entities.CreatureCard;
import app.entities.Magic;
import app.entities.MagicCard;
import app.entities.Player;
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
    private List<CreatureCard> mockDeck;

    @BeforeEach
    void setUp() {
        magicCard = new MagicCard("DarkHole", 2, 2); // <-- not needed here ?
        magic = new Magic();
        monsterCard = new CreatureCard(10, "Ali", 5, 2, 2, 2, FIRE, false);
        player = new Player(mockDeck, "player1");
        cardsOnTable = new ArrayList();
    }

    @Test
    void selfHealPlayer() {
        int currentPlayerHp = player.getHp();
        magic.selfHealPlayer( player, 2 );
        int hpIncreased = player.getHp();
        assertEquals(currentPlayerHp + 2, hpIncreased);
    }

    @Test
    void damageEnemyPlayer() {
        int currentPlayerHp = player.getHp();
        magic.damageEnemyPlayer( player, 2 );
        int hpDecreased = player.getHp();
        assertEquals(currentPlayerHp - 2, hpDecreased);
    }

    @Test
    void healFriendlyCards() {
        int currentCardsHp = monsterCard.getHp();
        List<CreatureCard> tempList = new ArrayList<>();
        tempList.add(monsterCard);
        magic.healFriendlyCards( tempList, 2 );
        int cardsHpIncreased = monsterCard.getHp();
        assertFalse(cardsHpIncreased <= currentCardsHp );
        assertTrue( cardsHpIncreased > currentCardsHp );
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
        monsterCard.setHp(1);
        List<CreatureCard> tempList = new ArrayList<>();
        tempList.add(monsterCard);
        magic.damageEnemyCards( tempList, 10 );
        int cardsHpDecreased = tempList.get(0).getHp();

        System.out.println(cardsHpDecreased);

        assertTrue( cardsHpDecreased >= 0 );
    }

    @Test
    void healOneCard() {
        int currentCreatureCardHp = monsterCard.getHp();
        magic.healOneCard( monsterCard, 2 );
        int creatureCardHpIncreased = monsterCard.getHp();
//        assertEquals(currentCreatureCardHp + 2, creatureCardHpIncreased);
        assertFalse(currentCreatureCardHp <= creatureCardHpIncreased );
    }

    @Test
    void damageOneCard() {
        int currentCreatureCardHp = monsterCard.getHp();
        magic.damageOneCard( monsterCard, 2 );
        int creatureCardHpDecreased = monsterCard.getHp();
        assertEquals(currentCreatureCardHp - 2, creatureCardHpDecreased);
    }
}