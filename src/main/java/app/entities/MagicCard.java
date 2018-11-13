package app.entities;

import app.AttackType;
import app.MagicMethod;

public class MagicCard {
public class MagicCard implements GameCard{

    private String name;
    private int attackPoints;
    Player player;
    private MagicMethod magicMethod;
    private int energyCost;
    private int attackPoints;

    public MagicCard(String name, int energyCost, int attackPoints) {
        this.name = name;
        this.energyCost = energyCost;
        this.attackPoints = attackPoints;
    }

    public MagicMethod getMagicMethod() {
        return magicMethod;
    }

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
