package app.entities;

public class CreatureCard implements GameCard{
    private int hp;
    private String name;
    private int energyCost;
    private int attackPoints;
    private int defencePoint;
    private int coolDown;
    private Enum attackType;
    private boolean isUsed;

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public Boolean isCardDead() {
        return this.hp < 1;
    }

    public CreatureCard(String name){
        hp = 4; //Random 1-7
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

    public int getHp(){
        return hp;
    }

    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    public String getName() {
        return name;
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

    public void setAttackType(Enum attackType) {
        this.attackType = attackType;
    }
}
