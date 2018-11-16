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

    private int playerCard = -1;
    private int enemyCard = -1;

    public void setPlayerCard(int playerCard) {
        this.playerCard = playerCard + 1;
    }

    public void setEnemyCard(int enemyCard) throws IOException {
        this.enemyCard = enemyCard + 1;
        attack();
    }

    private void attack() throws IOException {
        if (playerCard >= 0) {
            System.out.println("ATTACK_CARD:"+ playerCard+":"+ enemyCard);
            ClientGame.getClientNetwork().sendMessage("ATTACK_CARD:"+ playerCard+":"+ enemyCard);
        }
    }
    public void endTurn() throws IOException {
        ClientGame.getClientNetwork().sendMessage("END_TURN");
    }
}
