package app.entities;

public class SpecialCreature extends CreatureCard {
    public enum SpecialAbility {
        ICE_BLAST
    }
    private SpecialAbility specialAbility;

    public SpecialCreature(int hp, String name, int energyCost, int attackPoints, int defencePoint, int coolDown, String attackType, boolean isUsed, String specialAbility) {
        super(hp, name, energyCost, attackPoints, defencePoint, coolDown, attackType, isUsed);
        this.specialAbility = SpecialAbility.valueOf(specialAbility);
    }

    public void setSpecialAbility(SpecialAbility specialAbility) {
        this.specialAbility = specialAbility;
    }

    public SpecialAbility getSpecialAbility(){
        return this.specialAbility;
    }
}
