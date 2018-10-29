package java.entities;

import java.entities.GameCard;
import java.util.List;

public class Player {
    private int hp;
    private List<GameCard> cardsOnHand;
    private List<GameCard> cardsInDeck;
    private List<GameCard> cardsOnTable;
    private boolean hasPlayedCard;

    public Player(int hp, List<GameCard> cardsOnHand, List<GameCard> cardsInDeck, List<GameCard> cardsOnTable) {
        this.hp = hp;
        this.cardsOnHand = cardsOnHand;
        this.cardsInDeck = cardsInDeck;
        this.cardsOnTable = cardsOnTable;
        this.hasPlayedCard = false;
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
