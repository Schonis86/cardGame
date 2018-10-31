package app.entities;

public class GameCard {
    private int hp;
    private String name;
    private boolean isUsed;

    public GameCard(String name){
        hp = 0; //Random 1-7
        this.name = name;
        isUsed = false;
    }

    public void increaseHp(int value){
    }

    public void decreaseHp(int value){
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
}
