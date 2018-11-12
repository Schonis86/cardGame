package app.entities;

import app.AttackType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CreatureCard implements Serializable {
    private int hp;
    private String name;
    private int energyCost;
    private int attackPoints;
    private int defencePoint;
    private int coolDown;
    private AttackType attackType;
    private boolean isUsed;


    @JsonCreator
    public CreatureCard(@JsonProperty("hp") int hp,
                        @JsonProperty("name") String name,
                        @JsonProperty("energyCost") int energyCost,
                        @JsonProperty("attackPoints") int attackPoints,
                        @JsonProperty("defencePoints") int defencePoint,
                        @JsonProperty("coolDown") int coolDown,
                        @JsonProperty("attackType") AttackType attackType,
                        @JsonProperty("isUsed") boolean isUsed) {
        this.hp = hp;
        this.name = name;
        this.energyCost = energyCost;
        this.attackPoints = attackPoints;
        this.defencePoint = defencePoint;
        this.coolDown = coolDown;
        this.attackType = attackType;
        this.isUsed = isUsed;
    }

    public CreatureCard(String name) {
        hp = 4; //Random 1-7
        attackPoints = 4;
        this.name = name;
        isUsed = false;
    }

    public void increaseHp(int heal) {
        int oldHp = getHp();
        int newHp = oldHp + heal;

        setHp(newHp);
    }

    public void decreaseHp(int damage) {
        int oldHp = getHp();
        int newHp = oldHp - damage;

        setHp(newHp);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }


    public boolean getIsUsed() {
        return isUsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore()
    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
    @JsonIgnore()
    public Boolean isCardDead() {
        return this.hp < 1;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefencePoint() {
        return defencePoint;
    }

    public void setDefencePoint(int defencePoint) {
        this.defencePoint = defencePoint;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public Enum getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }
}
