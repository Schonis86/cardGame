package client.component.gameBoard;

import app.dto.GameDto;
import app.entities.CreatureCard;
import app.entities.GameCard;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            renderEventBtn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            gameDto = ClientGame.getDto();
            new Thread(() -> {
                Platform.runLater(() -> {
                    try {
                        assignCards();
                        printCardsOnHand();
                        printCardsOnBoard(playerCards, enemyCards);
                        printPlayerInfo();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }).start();
        } catch (Exception e) {
        }
    }


    //    Måste ändra namn till något bra!
    private void assignCards() {

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


    private void printCardsOnHand() throws IOException {
        CARD_GRIDPANE.getChildren().clear();
        CARD_GRIDPANE.addRow(0);

        List<GameCard> cardList = ClientGame.getDto().getCardsOnHand();

        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i) instanceof CreatureCard) {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane pane = loader.load(getClass().getResource("/creatureCard.fxml").openStream());
                CreatureCardController controller = loader.getController();
                controller.setValues((CreatureCard) cardList.get(i), i, "hand");
                CARD_GRIDPANE.addColumn(i, pane);

            } else {
                // LÄGG TILL MAGIC CARDS
            }
        }
    }

    private void printCardsOnBoard(List<CreatureCard> playerCards, List<CreatureCard> enemyCards) throws IOException {
        printCardsOnHand();
        printCardsOnTable(PLAYER_CARDS_ON_TABLE, playerCards, "playerTable");
        printCardsOnTable(ENEMY_CARDS_ON_TABLE, enemyCards, "enemyTable");
    }

   private void printCardsOnTable(GridPane thePane, List<CreatureCard> cards, String table) throws IOException {
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

    private void printPlayerInfo() {
        PLAYER_HP_PROGRESSBAR.setProgress(playerHp / 10);
        PLAYER_NAME.setText(ClientGame.getPlayer());
        ENEMY_HP_PROGRESSBAR.setProgress(enemyHp / 10);
    }

    private void renderEventBtn() throws IOException {
        GridPane gridPane = FXMLLoader.load(getClass().getResource("/eventbuttons.fxml"));
        BTN_ANCHOR_PANE.getChildren().setAll(gridPane);
    }

}
