package app.entities;

public class MagicCard implements GameCard{

    private String name;
    private int attackPoints;
    // LINAS EGNA TILLÄGG FÖR ATT FÅ DATABAS ATT FUNKA!






    //SLUT PÅ LINAS EGNA TILLÄGG FÖR ATT FÅ DATABAS ATT FUNKA!
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //omedelbar effekt - öka Player1/2 HP med 3
    public void selfHeal() {

    }

    // riktad effek - ge direkt skada till motståndarens kort med 2
    public void damageEnemyCards() {

    }

    // riktad effek - öka ett korts Hp med 2

    public int getAttackPoints(){
        return attackPoints;
    }
}
