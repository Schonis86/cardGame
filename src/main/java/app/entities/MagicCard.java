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

    //omedelbar effekt - öka Player1/2 HP med 3
    public void selfHealPlayer( Player player ) {
        player.setHp( player.getHp() + 2 );
    }

    //omedelbar effekt - ge skada till Player1/2 HP med 2
    public void damageEnemyPlayer( Player player ) {

    }

    // riktad effek - ge direkt skada till motståndarens kort med 2
    public void damageEnemyCards( int damage ) {

    }

    // riktad effek - öka ett korts Hp med 2
    public void increaseCradHp() {

    }
}
