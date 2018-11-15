package client.component.creatureCard;

import app.entities.CreatureCard;
import client.ActionClass;
import client.ClientGame;
import client.component.eventButtons.EventButtonsController;
import client.component.gameBoard.GameBoardController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


import java.awt.event.ActionEvent;
import java.io.IOException;


public class CreatureCardController {

    public AnchorPane CREATURE_CARD ;

    public Label CARD_NAME;

    public Label CARD_HP;

    private int index;
    private String table;
    private boolean clicked = false;


    public void setValues(CreatureCard card, int index, String value) {
        this.table = value;
        this.index = index;
        CARD_NAME.setText(card.getName());
        CARD_HP.setText(Integer.toString(card.getHp()));
    }


    public void onClick() throws IOException {
        switch (table) {
            case "hand":
                playCardOnTable();
                break;
            case "playerTable":
                handlePlayerCardsOnTable();
                break;
            case "enemyTable":
                handleEnemyTable();
        }

    }

    private void handleEnemyTable() throws IOException {
        setBorderColor();
        ActionClass.getInstance().setEnemyCard(index);
    }

    private void handlePlayerCardsOnTable() {
        setBorderColor();
        ActionClass.getInstance().setPlayerCard(index);

    }

    private void playCardOnTable() throws IOException {
        ClientGame.getClientNetwork().sendMessage("PLAY_CARD:" + (index + 1));
    }

    private void setBorderColor() {
        int depth = 70;
        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.RED);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);

        CREATURE_CARD.setEffect(borderGlow);
    }

}
