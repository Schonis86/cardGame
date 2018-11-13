package app.entities;

import app.gui.Print;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int energyPoints;
    private int hp;
    private List<CreatureCard> cardsOnHand;
    private List<CreatureCard> cardsInDeck;
    private List<CreatureCard> cardsOnTable;
    private List<CreatureCard> graveYard;

    private boolean hasPlayedCard;

    public Player(List<GameCard> deck, String name) {
        this.name = name;
        this.hp = 10;
        this.cardsInDeck = deck;
        this.cardsOnHand = new ArrayList();
        this.cardsOnTable = new ArrayList();
        this.graveYard = new ArrayList();
        this.hasPlayedCard = false;
        this.energyPoints = 10;
        getStartCards();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public List<CreatureCard> getCardsOnHand() {
        return cardsOnHand;
    }

    public void setCardsOnHand(List<CreatureCard> cardsOnHand) {
        this.cardsOnHand = cardsOnHand;
    }

    public List<CreatureCard> getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(List<CreatureCard> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }

    public List<CreatureCard> getCardsOnTable() {
        return cardsOnTable;
    }

    public void setCardsOnTable(List<CreatureCard> cardsOnTable) {
        this.cardsOnTable = cardsOnTable;
    }

    public List<CreatureCard> getGraveYard() {
        return graveYard;
    }

    public void setGraveYard(List<CreatureCard> graveYard) {
        this.graveYard = graveYard;
    }

    public boolean isHasPlayedCard() {
        return hasPlayedCard;
    }

    public void setHasPlayedCard(boolean hasPlayedCard) {
        this.hasPlayedCard = hasPlayedCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergyPoints() {
        return energyPoints;
    }

    public void setEnergyPoints(int energyPoints) {
        this.energyPoints = energyPoints;
    }

    public void regenerateEnergy(int points) {
        this.energyPoints += points;
    }

    public void getStartCards() {
        final int N_CARDS_ON_HAND_AT_START = 5;

        for (int i = 0; i < N_CARDS_ON_HAND_AT_START; i++) {
            drawCard();
        }
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

    public void playCard(int index) throws Exception {
        if(index >= 0 && index < cardsOnHand.size()) {
            if (!hasPlayedCard) {
                cardsOnTable.add(cardsOnHand.get(index));
                cardsOnHand.remove(index);
                hasPlayedCard = true;
                Print.actionMessage("    Played card: " + (index + 1) + "    ");
                System.out.println(" ");
            } else {
                throw new Exception("You can only play 1 card each round !");
            }
        } else {
            throw new Exception("Card doesn't exist!");
        }
    }

    public void removeCardIfDead() {
        cardsOnTable.stream()
                .filter(c -> c.getHp() <= 0)
                .forEach(card -> graveYard.add(card));
        cardsOnTable.removeIf(card -> card.getHp() < 0);
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
