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

    @FXML
    public Button ATTACK_BTN;

    @FXML
    public Button END_TURN_BTN;

    private GameDto gameDto;
    private String currentPlayer;
    private boolean playerOneTurn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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


    @FXML
    private void endTurn() throws IOException {
        ClientGame.getClientNetwork().sendMessage("END_TURN");
    }



}
