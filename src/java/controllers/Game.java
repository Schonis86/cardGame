package java.controllers;

import javax.smartcardio.Card;
import java.entities.GameCard;
import java.entities.Player;
import java.util.List;

public class Game {

    boolean playerTurnToggle;

    private int roundCounter = 0;
    private int turnCounter = 0;
    List<GameCard> allCards;
    List<GameCard> player1Cards;
    List<GameCard> player2Cards;

    public Game() {
        devideCards();
        Player player1 = new Player(player1Cards);
        Player player2 = new Player(player2Cards);
    }

    private void devideCards() {

    }

    private List<GameCard> seedDecks() {
         return null;
    }

    public void toggleTurn() {

    }

    public void print() {

    }

    public void printWinningMessage() {

    }

    public void playCard(Card card) {

    }

    public void attack(Card card, Card card2) {

    }

    public void attackPlayer() {

    }

    public void  getUserInput() {

    }

    public void checkDeath() {

    }

    public void roundCheck() {

    }

    public void killPlayer() {

    }

    public void randomNumber() {

    }
}

