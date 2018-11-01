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
        this.cardsOnHand = new ArrayList();
        this.cardsOnTable = new ArrayList();
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
        if (cardsInDeck.size() > 0) {
            cardsOnHand.add(cardsInDeck.get(cardsInDeck.size() - 1));
            cardsInDeck.remove(cardsInDeck.size() - 1);
        } else {
            System.out.println("can't draw card!");
        }

    }

    public void playCard(int index) {


        if ( !hasPlayedCard ) {
            cardsOnTable.add(cardsOnHand.get(index));
            cardsOnHand.remove(index);
            hasPlayedCard = true;
        }

    }

    public void getIsCardDead(String cardName) {

        GameCard card = cardsOnTable
                .stream()
                .filter(c -> c.getName().equals(cardName))
                .findFirst()
                .get();

        if (card.isCardDead) {
            cardsOnTable.removeIf(c -> c.getName().equals(cardName));
        }

    }

    public void reduceHp(int damage) {
        int oldHp = getHp();
        int newHp = oldHp - damage;

        setHp(newHp);
    }

    public void increaseHp(int heal) {
        int oldHp = getHp();
        int newHp = oldHp + heal;

        setHp(newHp);
    }

}
