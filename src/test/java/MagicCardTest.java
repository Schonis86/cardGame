import app.entities.CreatureCard;
import app.entities.MagicCard;
import app.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MagicCardTest {

    private MagicCard magicCard;
    private CreatureCard monsterCard;
    private Player player;

    @Mock
    private List<CreatureCard> mockDeck;

    @BeforeEach
    void setUp() {
        magicCard = new MagicCard("DarkHole");
        monsterCard = new CreatureCard("IceGolem");
        player = new Player(mockDeck, "player1");

    }

    @Test
    void damageEnemyCards() {
        magicCard.damageEnemyCards(2);
    }

    @Test
    void selfHealPlayer() {
        int currentPlayerHp = player.getHp();
        magicCard.selfHealPlayer( player );
        int hpIncreased = player.getHp();
        assertEquals(currentPlayerHp + 2, hpIncreased);
    }
}