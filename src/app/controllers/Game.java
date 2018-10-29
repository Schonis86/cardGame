package app.controllers;

import app.entities.GameCard;

import javax.smartcardio.Card;

import app.entities.Player;
import java.util.List;

public class Game {

    private boolean player1Turn;
    private int roundCounter = 0;
    private int turnCounter = 0;
    private List<GameCard> allCards;
    private List<GameCard> player1Cards;
    private List<GameCard> player2Cards;

    public Game() {
        devideCards();
        Player player1 = new Player(player1Cards);
        Player player2 = new Player(player2Cards);
        this.player1Turn = true;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    public void setPlayer1Turn(boolean player1Turn) {
        this.player1Turn = player1Turn;
    }

    public List<GameCard> getAllCards() {
        return allCards;
    }

    public List<GameCard> getPlayer1Cards() {
        return player1Cards;
    }

    public List<GameCard> getPlayer2Cards() {
        return player2Cards;
    }

    public void devideCards() {
//        List<GameCard> player1Deck;
//        List<GameCard> player2Deck;
//        Collections.shuffle(allCards);
//        player1Deck = allCards.subList(0, deck.size() / 2);
//        player2Deck = allCards.subList(deck.size() / 2, deck.size());
//        return 10;

    }

    public void toggleTurn() {
        setPlayer1Turn(!player1Turn);
        setTurnCounter(getTurnCounter() + 1);
    }

    public void print(String message) {

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

