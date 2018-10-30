package app.controllers;

import app.entities.GameCard;

import javax.smartcardio.Card;

import app.entities.Player;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {

    private boolean player1Turn;
    private int roundCounter = 0;
    private int turnCounter = 0;
    private List<GameCard> allCards;
    private List<GameCard> player1Cards;
    private List<GameCard> player2Cards;

    public Game(List<GameCard> deck) {
        this.allCards = deck;
        devideCards();
        Player player1 = new Player(player1Cards);
        Player player2 = new Player(player2Cards);
        this.player1Turn = true;
    }

    public void setPlayer1Cards(List<GameCard> player1Cards) {
        this.player1Cards = player1Cards;
    }

    public void setPlayer2Cards(List<GameCard> player2Cards) {
        this.player2Cards = player2Cards;
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
        Collections.shuffle(allCards);
        setPlayer1Cards(allCards.subList(0, allCards.size() / 2));
        setPlayer2Cards(allCards.subList(allCards.size() / 2, allCards.size()));
    }

    public void toggleTurn() {
        setPlayer1Turn(!player1Turn);
        setTurnCounter(getTurnCounter() + 1);

        String message = isPlayer1Turn() ? "Player 1 turn" : "Player 2 turn";
        print(message);

        getUserInput();
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

    public int randomNumber(int maxValue) {
        Random random = new Random();
        return random.nextInt(maxValue) + 1;
    }
}

