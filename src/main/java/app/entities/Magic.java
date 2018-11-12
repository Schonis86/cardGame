package app.entities;

import java.util.List;

public class Magic {

    Player player;
    CreatureCard creatureCard;

    //omedelbar effekt - öka Player1/2 HP med 2
    public void selfHealPlayer( Player player ) {
        player.setHp( player.getHp() + 2 );
    }

    //omedelbar effekt - ge skada till Player1/2 HP med 2
    public void damageEnemyPlayer( Player player ) {
        player.setHp( player.getHp() - 2 );
    }

    // omedelbar effek - ge direkt skada till motståndarens kort med 2
    public void damageEnemyCards( List<CreatureCard> cardsOnTable ) {

    }

    // omedelbar effek - öka sina egna korts HP med 2
    public void healFriendlyCards( List<CreatureCard> cardsOnTable ) {
        cardsOnTable.stream()
                .forEach( card -> card.setHp( creatureCard.getHp() + 2 ) );
    }

    // riktad effekt - öka ett korts HP med 2
    public void healOneCard( CreatureCard creatureCard ) {

    }

    // riktad effekt - minska ett korts HP med 2
    public void damageOneCard( CreatureCard creatureCard ) {

    }
}
