import app.entities.CreatureCard;
import app.entities.Magic;
import app.entities.MagicCard;
import app.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MagicTest {

    private MagicCard magicCard; // <-- not needed here ?
    private Magic magic;
    private CreatureCard monsterCard;
    private Player player;

    @Mock
    private List<CreatureCard> mockDeck;

    @BeforeEach
    void setUp() {
        magicCard = new MagicCard("DarkHole"); // <-- not needed here ?
        magic = new Magic();
        monsterCard = new CreatureCard("IceGolem");
        player = new Player(mockDeck, "player1");

    }

    @Test
    void selfHealPlayer() {
        int currentPlayerHp = player.getHp();
        magic.selfHealPlayer( player );
        int hpIncreased = player.getHp();
        assertEquals(currentPlayerHp + 2, hpIncreased);
    }

    @Test
    void damageEnemyPlayer() {
    }

    @Test
    void healFriendlyCards() {
    }
}