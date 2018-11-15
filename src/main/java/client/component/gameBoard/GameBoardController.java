package client.component.gameBoard;

import app.dto.GameDto;
import app.entities.CreatureCard;
import app.entities.GameCard;
import app.entities.MagicCard;
import client.ClientGame;
import client.component.creatureCard.CreatureCardController;
import client.component.eventButtons.EventButtonsController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {

    @FXML
    public GridPane ENEMY_CARDS_ON_TABLE, PLAYER_CARDS_ON_TABLE, CARD_GRIDPANE;
    @FXML
    public AnchorPane BOTTOM_PANE, TOP_PANE, BTN_ANCHOR_PANE;
    @FXML
    public ProgressBar PLAYER_HP_PROGRESSBAR, ENEMY_HP_PROGRESSBAR;
    @FXML
    public Label PLAYER_NAME;

    GameDto gameDto;
    List<CreatureCard> playerCards, enemyCards;
    int playerHp, enemyHp;

    EventButtonsController eventButtonsController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/eventbuttons.fxml"));
            loader.load();
            eventButtonsController = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        System.out.println("UPDATE");
        try {
            gameDto = ClientGame.getDto();
            new Thread(() -> {
                Platform.runLater(() -> {
                    try {
                        assignCards();
                        printCardsOnHand();
                        //         printCardsOnBoard(playerCards, enemyCards);
                        printPlayerInfo();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
            }).start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //    Måste ändra namn till något bra!
    public void assignCards() {

        switch (ClientGame.getPlayer()) {
            case " player1":
                playerHp = ClientGame.getDto().getPlayer1Hp();
                enemyHp = ClientGame.getDto().getPlayer2Hp();
                playerCards = ClientGame.getDto().getPlayer1CardsOnTable();
                enemyCards = ClientGame.getDto().getPlayer2CardsOnTable();
                break;
            case " player2":
                playerHp = ClientGame.getDto().getPlayer2Hp();
                enemyHp = ClientGame.getDto().getPlayer1Hp();
                playerCards = ClientGame.getDto().getPlayer2CardsOnTable();
                enemyCards = ClientGame.getDto().getPlayer1CardsOnTable();
                break;
            default:
                System.out.println("NO PLAYER");
                break;
        }
    }


    public void printCardsOnHand() throws IOException {
        CARD_GRIDPANE.getChildren().clear();
        CARD_GRIDPANE.addRow(0);
        List<GameCard> cardList = ClientGame.getDto().getCardsOnHand();

        for (int i = 0; i < cardList.size(); i++) {

            if (!(cardList.get(i) instanceof MagicCard)) {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane pane = loader.load(getClass().getResource("/creatureCard.fxml").openStream());
                CreatureCardController controller = loader.getController();
                controller.setValues((CreatureCard) cardList.get(i), i, "hand");
                CARD_GRIDPANE.addColumn(i, pane);
            } else {
            }
        }
        System.out.println(CARD_GRIDPANE.getChildren().size());
    }

    public void printCardsOnBoard(List<CreatureCard> playerCards, List<CreatureCard> enemyCards) throws IOException {
        printCardsOnHand();
        printCardsOnTable(PLAYER_CARDS_ON_TABLE, playerCards, "playerTable");
        printCardsOnTable(ENEMY_CARDS_ON_TABLE, enemyCards, "enemyTable");
    }

    public void printCardsOnTable(GridPane thePane, List<CreatureCard> cards, String table) throws IOException {
        thePane.getChildren().clear();
        thePane.addRow(0);
        if (cards != null) {
            for (int i = 0; i < cards.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane pane = loader.load(getClass().getResource("/creatureCard.fxml").openStream());
                CreatureCardController controller = loader.getController();
                controller.setValues(cards.get(i), i, table);
                thePane.addColumn(i, pane);
            }
        }
    }

    public void printPlayerInfo() {

        PLAYER_HP_PROGRESSBAR.setProgress(playerHp / 10);
        PLAYER_NAME.setText(ClientGame.getPlayer());
        ENEMY_HP_PROGRESSBAR.setProgress(enemyHp / 10);

    }

    public void renderEventBtn() throws IOException {
        GridPane gridPane = FXMLLoader.load(getClass().getResource("/eventbuttons.fxml"));
        BTN_ANCHOR_PANE.getChildren().setAll(gridPane);

    }

}
