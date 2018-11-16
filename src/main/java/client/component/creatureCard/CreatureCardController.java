package client.component.creatureCard;

import app.entities.CreatureCard;
import client.ActionClass;
import client.ClientGame;
import client.component.eventButtons.EventButtonsController;
import client.component.gameBoard.GameBoardController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CreatureCardController {

    @FXML
    public AnchorPane CARDS_ON_HAND;

    @FXML
    public AnchorPane CREATURE_CARD;
    @FXML
    public Label CARD_NAME;
    @FXML
    public Label CARD_HP;

    private int index;
    private String table;
    private boolean isUsed;
    private String currentPlayer;
    private boolean playerOneTurn;
    private boolean turn;


    public void setValues(CreatureCard card, int index, String value) {
        this.table = value;
        this.index = index;
        this.isUsed = card.getIsUsed();
        CARD_NAME.setText(card.getName());
        CARD_HP.setText(Integer.toString(card.getHp()));
        checkIfUsedAndDisable();
        getPlayerAndPlayerTurn();
    }

    private void checkIfUsedAndDisable() {
        if (isUsed && table.equals("playerTable")) {
            CREATURE_CARD.setDisable(true);
        }
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

    private void getPlayerAndPlayerTurn() {
        currentPlayer = ClientGame.getPlayer();
        playerOneTurn = ClientGame.getDto().getPlayerOneTurn();

        switch (currentPlayer) {
            case " player1":
                if (!playerOneTurn) {
                    this.CREATURE_CARD.setDisable(true);
                }
                break;
            case " player2":
                if (playerOneTurn) {
                    this.CREATURE_CARD.setDisable(true);
                }
                break;
        }
    }


}
