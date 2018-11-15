package client.component.creatureCard;

import app.entities.CreatureCard;
import client.ClientGame;
import client.component.eventButtons.EventButtonsController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


import java.io.IOException;


public class CreatureCardController {

    @FXML
    public AnchorPane CREATURE_CARD;
    @FXML
    public Label CARD_NAME;
    @FXML
    public Label CARD_HP;

    private int index;
    private String table;
    private boolean clicked = false;

    @FXML
    public void setValues(CreatureCard card, int index, String value) {
        this.table = value;
        this.index = index;
        CARD_NAME.setText(card.getName());
        CARD_HP.setText(Integer.toString(card.getHp()));
    }


    public void onClick(MouseEvent mouseEvent) throws IOException {
        switch (table) {
            case "hand":
                playCardOnTable();
                break;
            case "playerTable":
                handlePlayerCards();
                break;
            case "enemyTable":
                handleEnemyTable();
        }

    }

    private void handleEnemyTable() {
        setBorderColor();
        EventButtonsController.getInstance().setCardTwo(index);
    }

    private void handlePlayerCards() {
        setBorderColor();
        EventButtonsController.getInstance().setCardOne(index);
    }

    private void playCardOnTable() throws IOException {
        ClientGame.getClientNetwork().sendMessage("PLAY_CARD:" + (index + 1));
    }

    private void playerCardOnTable() {

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
