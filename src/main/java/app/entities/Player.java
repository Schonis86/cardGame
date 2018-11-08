package app.entities;

import app.gui.Print;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int hp;
    private List<GameCard> cardsOnHand;
    private List<GameCard> cardsInDeck;
    private List<GameCard> cardsOnTable;
    private List<GameCard> graveYard;
    private boolean hasPlayedCard;

    public Player(List<GameCard> deck, String name) {
        this.name = name;
        this.hp = 10;
        this.cardsInDeck = deck;
        this.cardsOnHand = new ArrayList();
        this.cardsOnTable = new ArrayList();
        this.graveYard = new ArrayList();
        this.hasPlayedCard = false;
        getStartCards();
    }

    public void getStartCards() {
        final int N_CARDS_ON_HAND_AT_START = 5;

        for (int i = 0; i < N_CARDS_ON_HAND_AT_START; i++) {
            drawCard();
        }
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

    public List<GameCard> getGraveYard() {
        return graveYard;
    }

    public void setGraveYard(List<GameCard> graveYard) {
        this.graveYard = graveYard;
    }

    public boolean isHasPlayedCard() {
        return hasPlayedCard;
    }

    public void setHasPlayedCard(boolean hasPlayedCard) {
        this.hasPlayedCard = hasPlayedCard;
    }

    public void drawCard() {
        final int N_CARDS_IN_DECK = cardsInDeck.size();
        final int INDEX_OF_LAST_CARD = N_CARDS_IN_DECK - 1;

        if (N_CARDS_IN_DECK > 0) {
            cardsOnHand.add(cardsInDeck.get(INDEX_OF_LAST_CARD));
            cardsInDeck.remove(INDEX_OF_LAST_CARD);
        } else {
            System.out.println("can't draw card!");
        }

    }

    public void playCard(int index) {

        if (!hasPlayedCard) {
            cardsOnTable.add(cardsOnHand.get(index));
            cardsOnHand.remove(index);
            hasPlayedCard = true;
            Print.actionMessage("    Played card: " + (index + 1) + "    ");
            System.out.println(" ");
        } else {
            Print.actionMessage("You can only play one card!");
        }
    }

    public void removeCardIfDead() {
        cardsOnTable.stream()
                .filter(c -> c.getHp() <= 0)
                .forEach(card -> graveYard.add(card));
        cardsOnTable.removeIf(card -> card.getHp() == 0);
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

    public void setCardsToUnUsed() {
        cardsOnTable.forEach(card -> card.setIsUsed(false));
    }

}
