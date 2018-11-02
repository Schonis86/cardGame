package app.controllers;

import app.entities.GameCard;

import javax.smartcardio.Card;
import javax.swing.text.StyledEditorKit;

import app.entities.Player;
import app.gui.Print;

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
        player1 = new Player(player1Cards, "Jonas");
        player2 = new Player(player2Cards, "Robin");
        System.out.println("Lina");
        this.player1Turn = false;
        toggleTurn();
    }


    public void divideCards() {
        if (allCards != null) {
            Collections.shuffle(allCards);
            ArrayList<GameCard> p1List = new ArrayList<GameCard>(allCards.subList(0, allCards.size()/2));
            ArrayList<GameCard> p2List = new ArrayList<GameCard>(allCards.subList(allCards.size()/2, allCards.size()));
            setPlayer1Cards(p1List);
            setPlayer2Cards(p2List);
        }

    }

    public void toggleTurn() {

        setPlayer1Turn(!player1Turn);
        setTurnCounter(getTurnCounter() + 1);
        roundCheck();
        String message = isPlayer1Turn() ? "Player 1 turn" : "Player 2 turn";
        Print.actionMessage(message);

        getUserInput();
    }

    public void getUserInput() {
        Player defendingPlayer;
        Player attackingPlayer;

        Boolean endTurn = false;
        if (isPlayer1Turn()) {
            attackingPlayer = player1;
            defendingPlayer = player2;
        } else {
            attackingPlayer = player2;
            defendingPlayer = player1;
        }

        while (!endTurn) {
            Print.cardsVisibleForActivePlayer(attackingPlayer,defendingPlayer);
            System.out.println("Choose option:");
            System.out.println("1. Play card");
            System.out.println("2. Attack Card");
            System.out.println("3. Attack Player");
            System.out.println("4. End Turn");

            Scanner scanner = new Scanner(System.in);
            int option = Integer.parseInt(scanner.nextLine());

            int chosenCard;

            switch (option) {
                case 1:
                    chosenCard = 0;
                    while (chosenCard < 1 || chosenCard > attackingPlayer.getCardsOnHand().size()) {
                        Print.actionMessage("Choose card to play!");
                        Print.optionList(attackingPlayer.getCardsOnHand());
                        chosenCard = Integer.parseInt(scanner.nextLine());
                    }
                    attackingPlayer.playCard(chosenCard - 1);
                    break;
                case 2:
                    if(attackingPlayer.getCardsOnTable().size()==0 || defendingPlayer.getCardsOnTable().size()==0){
                        Print.actionMessage("Attack not possible!");
                        break;
                    }
                    int attackCardNumber = 0;
                    int defendingCardNumber = 0;
                    while (attackCardNumber < 1 || attackCardNumber > attackingPlayer.getCardsOnTable().size()) {
                        Print.actionMessage("Choose a card to attack with");
                        Print.optionList(attackingPlayer.getCardsOnTable());
                        attackCardNumber = scanner.nextInt();
                    }
                    while (defendingCardNumber < 1 || defendingCardNumber > defendingPlayer.getCardsOnTable().size()) {
                        Print.actionMessage("Choose a card to attack");
                        Print.optionList(defendingPlayer.getCardsOnTable());
                        defendingCardNumber = scanner.nextInt();
                    }
                    GameCard attackingCard = attackingPlayer.getCardsOnTable().get(attackCardNumber - 1);
                    GameCard defendingCard = defendingPlayer.getCardsOnTable().get(defendingCardNumber - 1);
                    attack(attackingCard, defendingCard);
                    break;
                case 3:

                    if(defendingPlayer.getCardsOnTable().size()!=0 || attackingPlayer.getCardsOnTable().size() == 0){
                        Print.actionMessage("Can not attack player");
                        break;
                    }else{
                        int number = randomNumber(5);
                        attackPlayer(defendingPlayer, number);
                        Print.actionMessage(attackingPlayer.getName() + " attacked " + defendingPlayer.getName() + " with " + number + " damage!" );
                    }

                    break;
                case 4:
                    endTurn = true;
                    break;
                default:
                    System.out.println("Invalid option");
            }

        }
        attackingPlayer.setHasPlayedCard(false);
        toggleTurn();
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
        player1.getIsCardDead(player1Card.getName());
        player2.getIsCardDead(player2Card.getName());
        System.out.println(player1Card.getName() + " has attacked " + player2Card.getName());
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

