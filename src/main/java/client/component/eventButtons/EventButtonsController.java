package client.component.eventButtons;

import app.dto.GameDto;
import client.ClientGame;
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

    public static EventButtonsController instance;

    public EventButtonsController(){}

    public static EventButtonsController getInstance(){
        if(instance == null){
            instance = new EventButtonsController();
        }
        return instance;
    }


    public Button ATTACK_BTN;

    public Button END_TURN_BTN;

    private GameDto gameDto;
    private String currentPlayer;
    private boolean playerOneTurn;
    private int cardOne;
    private int cardTwo;

    public void setCardOne(int cardOne) {
        this.cardOne = cardOne;
    }

    public void setCardTwo(int cardTwo) {
        this.cardTwo = cardTwo;
    }

    public void update() {
        try {
            gameDto = ClientGame.getDto();
            new Thread(() -> {
//                Platform.runLater(this::disableEndTurnButton);
            }).start();
        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Button END_TURN_BTN = new Button();
        Button ATTACK_BTN = new Button();

        END_TURN_BTN.setOnAction(  (event -> {
            System.out.println("test");
           /* try {
                endTurn();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }));

    }

    public void attack(ActionEvent actionEvent) throws IOException {
        System.out.println("CardOne:" + cardOne);
        System.out.println("CardTwo:" + cardTwo);
    }

    private void endTurn() throws IOException {
        ClientGame.getClientNetwork().sendMessage("END_TURN");
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
