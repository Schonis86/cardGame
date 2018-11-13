package app.entities;

import java.util.List;
import java.util.Random;

public class Magic {

    Player player;
    CreatureCard creatureCard;

    //omedelbar effekt - öka Player1/2 HP med 2
    public void selfHealPlayer( Player player, int healPoints ) {
        player.setHp( player.getHp() + 2 );
    }

    //omedelbar effekt - ge skada till Player1/2 HP med 2
    public void damageEnemyPlayer( Player player, int attackPoints ) {
        player.setHp( player.getHp() - 2 );
    }

    // omedelbar effek - ge direkt skada till motståndarens kort med 2
    public void damageEnemyCards( List<CreatureCard> cardsOnTable, int attackPoints ) {

        int damage = getRandomPoints( attackPoints );

        cardsOnTable.stream()
            .forEach( card -> card.setHp( card.getHp() - damage ));
    }

    // omedelbar effek - öka sina egna korts HP med 2
    public void healFriendlyCards( List<CreatureCard> cardsOnTable, int healPoints ) {

        int heal = getRandomPoints( healPoints );

        cardsOnTable.stream()
            .forEach( card -> card.setHp( card.getHp() + heal ) );
    }

    // riktad effekt - öka ett korts HP med 2
    public void healOneCard( CreatureCard creatureCard, int healPoints ) {

        int heal = getRandomPoints( healPoints );

        creatureCard.setHp( creatureCard.getHp() + heal );
    }

    // riktad effekt - minska ett korts HP med 2
    public void damageOneCard( CreatureCard creatureCard, int attackPoints ) {
        creatureCard.setHp( creatureCard.getHp() - 2 );
    }

    private int getRandomPoints( int number ) {
        Random r = new Random();
        return r.nextInt( number ) + 1;
    }
}
