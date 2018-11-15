package client.component.eventButtons;

import app.dto.GameDto;
import client.ClientGame;
import client.component.gameBoard.GameBoardController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventButtonsController implements Initializable {
    private GameBoardController controller;

    @FXML
    public Button ATTACK_BTN;

    @FXML
    public Button END_TURN_BTN;

    private GameDto gameDto;
    private String currentPlayer;
    private boolean playerOneTurn;
    private int cardOne;
    private int cardTwo;

    public void setCardOne(int cardOne) {
        this.cardOne = cardOne;
        System.out.println(cardOne);
    }

    public void setCardTwo(int cardTwo) {
        this.cardTwo = cardTwo;
    }

    public void update() {
        try {
            gameDto = ClientGame.getDto();
            new Thread(() -> {
                System.out.println("Test från Knapparna");
            }).start();
        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void endTurn() throws IOException {
        ClientGame.getClientNetwork().sendMessage("END_TURN");
    }

    public void init(GameBoardController gameBoardController) {
        controller = gameBoardController;
    }


    /*public void disableEndTurnButton() {
        System.out.println(this.END_TURN_BTN);
        switch (currentPlayer) {
            case " player1":
                if (playerOneTurn) {
                    this.END_TURN_BTN.setDisable(false);
                } else {
                    this.END_TURN_BTN.setDisable(true);
                }
                break;
            case " player2":
                if (playerOneTurn) {
                    this.END_TURN_BTN.setDisable(true);
                } else {
                    this.END_TURN_BTN.setDisable(false);
                }
                break;
            default:
                System.out.println("Ingen spelare");
                break;
        }
    }*/

}
