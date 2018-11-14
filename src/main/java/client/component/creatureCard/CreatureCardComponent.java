package client.component.creatureCard;

import app.entities.CreatureCard;
import client.ClientGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class CreatureCardComponent {
    @FXML
    public Label CARD_NAME;
    @FXML
    public Label CARD_HP;
    @FXML
    public AnchorPane CREATURE_CARD;

    private int index;

    @FXML
    public void setIndex(int index) {
        this.index = index;
    }

    public AnchorPane RenderCard(CreatureCard card) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../creatureCard/creatureCard.fxml"));
        CARD_NAME = new Label();
        CARD_NAME.setLayoutX(13.0);
        CARD_NAME.prefHeight(17);
        CARD_NAME.prefWidth(114);
        CARD_NAME.setText(card.getName());

        CARD_HP = new Label();
        CARD_HP.prefHeight(17.0);
        CARD_HP.prefWidth(32.0);
        CARD_HP.setLayoutX(39.0);
        CARD_HP.setLayoutY(129.0);
        CARD_HP.setText(Integer.toString(card.getHp()));
        pane.getChildren().addAll(CARD_HP, CARD_NAME);
        return pane;
    }


    public void onClick(MouseEvent e) throws IOException {
       placeCardOnTable();
    }


    private void placeCardOnTable() throws IOException{
        ClientGame.getClientNetwork().sendMessage("PLAY_CARD:" );
    }

}
