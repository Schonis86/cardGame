package app.entities;

import java.util.List;
import java.util.Random;

public class Magic {

    Player player;
    CreatureCard creatureCard;

    //omedelbar effekt
    public void selfHealPlayer( Player player, int healPoints ) {
        int heal = getRandomPoints( healPoints );
        player.setHp( player.getHp() + heal >= player.getMaxHp() ? 20 : player.getHp() + heal );
    }

    //omedelbar effekt
    public void damageEnemyPlayer( Player player, int attackPoints ) {
        System.out.println("DAMAGE ENEMY PLAYER");
        int damage = getRandomPoints( attackPoints );
        player.setHp( damage >= player.getHp() ? 0 : player.getHp() - damage );
    }

    // omedelbar effek
    public void damageEnemyCards( List<CreatureCard> cardsOnTable, int attackPoints ) {

        int damage = getRandomPoints( attackPoints );

        cardsOnTable.stream()
            .forEach( card -> {
                int cardHp = card.getHp();
                card.setHp( damage > cardHp ? 0 : cardHp - damage );
            });
    }

    // omedelbar effekt
    public void healFriendlyCards( List<CreatureCard> cardsOnTable, int healPoints ) {

        int heal = getRandomPoints( healPoints );

        cardsOnTable.stream()
            .forEach( card -> {
                int maxHp = card.getHpMax();
                int damagedCreatureCard =  maxHp - card.getHp();
                card.setHp( heal >= damagedCreatureCard ? maxHp : card.getHp() + heal  );
            });
    }

    // riktad effekt
    public void healOneCard( CreatureCard creatureCard, int healPoints ) {
        System.out.println("heal one card");
        int maxHp = creatureCard.getHpMax();
        int heal = getRandomPoints( healPoints );
        int damagedCreatureCard = maxHp - creatureCard.getHp();
        creatureCard.setHp( heal >= damagedCreatureCard ? maxHp : creatureCard.getHp() + heal );
    }

    // riktad effekt
    public void damageOneCard( CreatureCard creatureCard, int attackPoints ) {
        System.out.println("damage on card");
        int damage = getRandomPoints( attackPoints );
        creatureCard.setHp( damage > creatureCard.getHp() ? 0 : creatureCard.getHp() - damage );
    }

    public int getRandomPoints( int number ) {
        Random r = new Random();
        return r.nextInt( number ) + 1;
    }
}
