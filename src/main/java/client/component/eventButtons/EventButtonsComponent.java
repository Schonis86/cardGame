package client.component.eventButtons;

import client.ClientGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventButtonsComponent implements Initializable {

    @FXML
    public Button ATTACK_BTN;
    @FXML
    public Button END_TURN_BTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void attack(ActionEvent actionEvent) throws IOException {

    }

    public void endTurn(ActionEvent actionEvent) throws IOException {
        ClientGame.getClientNetwork().sendMessage("END_TURN");

    }

    public void update() {
    }


}
