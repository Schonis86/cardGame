package client;

import java.io.IOException;

public class ActionClass {

    private static ActionClass instance;

    private ActionClass() { }

    public static ActionClass getInstance(){
        if(instance == null){
            instance = new ActionClass();
        }
        return instance;
    }

    private int playerCard;
    private int enemyCard;

    public void setPlayerCard(int playerCard) {
        this.playerCard = playerCard;
    }

    public void setEnemyCard(int enemyCard) throws IOException {
        this.enemyCard = enemyCard;
        attack();
    }

    private void attack() throws IOException {
        if (playerCard >= 0) {
            System.out.println("ATTACK_CARD:"+ playerCard+":"+ enemyCard);
            ClientGame.getClientNetwork().sendMessage("ATTACK_CARD:"+ playerCard+":"+ enemyCard);
        }
    }
}
