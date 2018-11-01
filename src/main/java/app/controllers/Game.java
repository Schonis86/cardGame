package app.controllers;

import app.entities.GameCard;

import javax.smartcardio.Card;
import javax.swing.text.StyledEditorKit;

import app.entities.Player;

import java.util.*;

public class Game {

    private boolean player1Turn;
    private int roundCounter = 0;
    private int turnCounter = 0;
    private List<GameCard> allCards;
    private List<GameCard> player1Cards;
    private List<GameCard> player2Cards;
    private Player player1;
    private Player player2;

    public Game(List<GameCard> deck) {
        this.allCards = deck;
        divideCards();
        player1 = new Player(player1Cards);
        player2 = new Player(player2Cards);
        this.player1Turn = true;
        this.player1Cards = new ArrayList<>();
        this.player2Cards = new ArrayList<>();
    }


    public void divideCards() {
        if (allCards != null) {
            Collections.shuffle(allCards);
            setPlayer1Cards(allCards.subList(0, allCards.size() / 2));
            setPlayer2Cards(allCards.subList(allCards.size() / 2, allCards.size()));
        }

    }

    public void toggleTurn() {
        setPlayer1Turn(!player1Turn);
        setTurnCounter(getTurnCounter() + 1);
        roundCheck();


        String message = isPlayer1Turn() ? "Player 1 turn" : "Player 2 turn";
        print(message);

        getUserInput();
    }

    public void print(String message) {

    }

    public void getUserInput() {
        Player defendingPlayer;
        Player attackingPlayer;
        Boolean endTurn = false;
        if (isPlayer1Turn()) {
            attackingPlayer = player1;
            defendingPlayer = player2;
            print("Player 1 turn");
        } else {
            attackingPlayer = player2;
            defendingPlayer = player1;
            print("Player 2 turn");
        }
        while (!endTurn) {
            print("Choose option:");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    int index = 0;
                    while (index < 1 || index > attackingPlayer.getCardsOnHand().size()) {
                        print("Choose card to play!");
                        index = scanner.nextInt();
                    }
                    attackingPlayer.playCard(index - 1);
                    break;
                case 2:

                    break;
                case 3:

                    break;

                default:

            }


        }


    }

    public void playCard(Card card) {

    }

    public void attack(GameCard attackingCard, GameCard defendingCard) {
        GameCard player1Card;
        GameCard player2Card;
        int player1FightingPoints;
        int player2FightingPoints;

        if (player1Turn) {
            player1Card = attackingCard;
            player2Card = defendingCard;
            player1Card.setIsUsed(true);
        } else {
            player2Card = attackingCard;
            player1Card = defendingCard;
            player2Card.setIsUsed(true);
        }
        do {
            player1FightingPoints = randomNumber(6);
            player2FightingPoints = randomNumber(6);
        } while (player1FightingPoints == player2FightingPoints);

        int fightResult = player1FightingPoints - player2FightingPoints;
        if (fightResult < 0) {
            player1Card.decreaseHp(-fightResult);
        } else {
            player2Card.decreaseHp(fightResult);
        }
        player1.killCard(player1Card.getName());
        player2.killCard(player2Card.getName());
    }


    public void attackPlayer(Player player, int attackNumber) {
        player.reduceHp(attackNumber);
        if (isPlayerDead(player)) {
            killPlayer(player);
        }
    }

    public boolean isPlayerDead(Player player) {
        if (player == null || player.getHp() == 0) {
            return true;
        } else return false;
    }

    public void roundCheck() {
        if (turnCounter % 2 != 0) {
            roundCounter++;
        }
    }

    public void killPlayer(Player player) {
        System.out.println("player killed");
    }

    public int randomNumber(int maxValue) {
        Random random = new Random();
        return random.nextInt(maxValue) + 1;
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
}

