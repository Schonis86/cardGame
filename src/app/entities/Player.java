package app.entities;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int hp;
    private List<GameCard> cardsOnHand;
    private List<GameCard> cardsInDeck;
    private List<GameCard> cardsOnTable;
    private boolean hasPlayedCard;

    public Player(List<GameCard> deck) {
        this.hp = 10;
        this.cardsInDeck = deck;
        this.cardsOnHand = getStartCards();
        this.cardsOnTable = new ArrayList<>();
        this.hasPlayedCard = false;
    }

    public List<GameCard> getStartCards() {
        return null;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public List<GameCard> getCardsOnHand() {
        return cardsOnHand;
    }

    public void setCardsOnHand(List<GameCard> cardsOnHand) {
        this.cardsOnHand = cardsOnHand;
    }

    public List<GameCard> getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(List<GameCard> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }

    public List<GameCard> getCardsOnTable() {
        return cardsOnTable;
    }

    public void setCardsOnTable(List<GameCard> cardsOnTable) {
        this.cardsOnTable = cardsOnTable;
    }

    public boolean isHasPlayedCard() {
        return hasPlayedCard;
    }

    public void setHasPlayedCard(boolean hasPlayedCard) {
        this.hasPlayedCard = hasPlayedCard;
    }

    public void drawCard() {

    }

    public void playCard() {

    }

    public void killCard(String cardName) {

    }

    public void reduceHp(int value) {

    }
    public void increaseHp(int value) {

    }

}