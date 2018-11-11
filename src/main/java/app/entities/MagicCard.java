package app.entities;

public class MagicCard {

    private String name;

    Player player;

    public MagicCard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //omedelbar effekt - öka Player1/2 HP med 2
    public void selfHealPlayer( Player player ) {
        player.setHp( player.getHp() + 2 );
    }

    //omedelbar effekt - ge skada till Player1/2 HP med 2
    public void damageEnemyPlayer( Player player ) {
        player.setHp( player.getHp() - 2 );
    }

    // omedelbar effek - ge direkt skada till motståndarens kort med 2
    public void damageEnemyCards( int damage ) {

    }

    // omedelbar effek - öka sina egna korts HP med 2
    public void healFriendlyCards() {

    }

    // riktad effekt - öka ett korts HP med 2
    public void healOneCard() {

    }

    // riktad effekt - minska ett korts HP med 2
    public void damageOneCard() {

    }
}
