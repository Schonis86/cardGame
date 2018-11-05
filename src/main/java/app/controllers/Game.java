package app.controllers;

import app.entities.GameCard;

import javax.smartcardio.Card;
import javax.swing.text.StyledEditorKit;

import app.entities.Player;
import app.gui.Print;
import net.bytebuddy.implementation.bytecode.Throw;

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
        this.player1Turn = false;
    }

    public void start(){
        toggleTurn();
    }


    public void divideCards() {
        if (allCards != null) {
            Collections.shuffle(allCards);
            ArrayList<GameCard> p1List = new ArrayList<GameCard>(allCards.subList(0, allCards.size() / 2));
            ArrayList<GameCard> p2List = new ArrayList<GameCard>(allCards.subList(allCards.size() / 2, allCards.size()));
            setPlayer1Cards(p1List);
            setPlayer2Cards(p2List);
        }

    }

    public void toggleTurn() {
        setPlayer1Turn(!player1Turn);

        setTurnCounter(getTurnCounter() + 1);
        roundCheck();
//        String message = isPlayer1Turn() ? "Player 1 turn" : "Player 2 turn";
//        Print.actionMessage(message);

        // drawCard
        if ( isPlayer1Turn()) {
            if ( player1.getCardsOnHand().size() < 5 ) {
                player1.drawCard();
            }
        }
        else {
            if ( player2.getCardsOnHand().size() < 5 ) {
                player2.drawCard();
            }
        }


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
        attackingPlayer.setCardsToUnUsed();
        while (!endTurn) {
            try {
                Print.cardsVisibleForActivePlayer(attackingPlayer, defendingPlayer);
                Print.actionMessage("    CHOOSE OPTION     ");
                System.out.print("| 1. PLAY CARD |");
                System.out.print("| 2. ATTACK CARD |");
                System.out.print("| 3. ATTACK PLAYER |");
                System.out.print("| 4. END TURN |");
                System.out.println("\n\n");

                Scanner scanner = new Scanner(System.in);
                int option = Integer.parseInt(scanner.nextLine());

                int chosenCard;

                switch (option) {
                    case 1:
                        chosenCard = 0;
                        while (chosenCard < 1 || chosenCard > attackingPlayer.getCardsOnHand().size()) {
                            Print.actionMessage("Choose card to play!");
                            Print.optionList(attackingPlayer.getCardsOnHand());
                            System.out.println(" ");
                            chosenCard = Integer.parseInt(scanner.nextLine());
                        }
                        attackingPlayer.playCard(chosenCard - 1);
                        break;
                    case 2:
                        if (roundCounter <= 1) {
                            throw new Exception("cant make attack move first round!");
                        }
                        if (attackingPlayer.getCardsOnTable().size() == 0 || defendingPlayer.getCardsOnTable().size() == 0) {
                            Print.actionMessage("Attack not possible!");
                            break;
                        }
                        int attackCardNumber = 0;
                        int defendingCardNumber = 0;
                        boolean cardIsPlayed = true;
                        while (attackCardNumber < 1 || attackCardNumber > attackingPlayer.getCardsOnTable().size() || cardIsPlayed) {
                            Print.actionMessage("Choose a card to attack with");
                            Print.optionList(attackingPlayer.getCardsOnTable());
                            attackCardNumber = scanner.nextInt();
                            if (attackCardNumber > 0 && attackCardNumber <= attackingPlayer.getCardsOnTable().size()) {
                                cardIsPlayed = attackingPlayer.getCardsOnTable().get(attackCardNumber - 1).isUsed();
                                if (cardIsPlayed) {
                                    throw new Exception("card has already attacked playing this round!");
                                }
                            }
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
                        if (roundCounter <= 1) {
                            throw new Exception("cant make attack move first round!");
                        }
                        if (defendingPlayer.getCardsOnTable().size() != 0 || attackingPlayer.getCardsOnTable().size() == 0) {
                            Print.actionMessage("Can not attack player");
                            break;
                        } else {
                            Print.actionMessage("Choose a card to attack player with");
                            int attackPlayerCardNumber = 0;
                            cardIsPlayed = true;
                            while (attackPlayerCardNumber < 1 || attackPlayerCardNumber > attackingPlayer.getCardsOnTable().size() || cardIsPlayed) {
                                Print.actionMessage("Choose card to play!");
                                Print.optionList(attackingPlayer.getCardsOnTable());
                                attackPlayerCardNumber = Integer.parseInt(scanner.nextLine());
                                if (attackPlayerCardNumber > 0 && attackPlayerCardNumber <= attackingPlayer.getCardsOnTable().size()) {
                                    cardIsPlayed = attackingPlayer.getCardsOnTable().get(attackPlayerCardNumber - 1).isUsed();
                                    if (cardIsPlayed) {
                                        throw new Exception("card has already attacked playing this round!");
                                    }
                                }
                            }
                            //  GameCard attackingPlayerCard = attackingPlayer.getCardsOnTable().get(attackPlayerCardNumber - 1);
                            //Korten har ingen damage än så vi kör randomNumber
                            int number = randomNumber(5);
                            attackPlayer(defendingPlayer, number);
                            Print.actionMessage(attackingPlayer.getName() + " attacked " + defendingPlayer.getName() + " with " + number + " damage!");
                            attackingPlayer.getCardsOnTable().get(attackPlayerCardNumber - 1).setIsUsed(true);
                        }

                        break;
                    case 4:
                        endTurn = true;
                        break;
                    default:
                        System.out.println("Invalid option");
                }

            } catch (Exception e) {
                Print.actionMessage(e.getMessage());
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
        if (player1Turn) {
            Print.actionMessage(player1Card.getName() + " HAS ATTACKED " + player2Card.getName());
        }else{
            Print.actionMessage(player2Card.getName() + " HAS ATTACKED " + player1Card.getName());
        }

    }


    public void attackPlayer(Player player, int attackNumber) {
        player.reduceHp(attackNumber);
        isPlayerDead(player);
    }

    public void isPlayerDead(Player player) {
        if (player == null || player.getHp() <= 0) {
            if (isPlayer1Turn()) {
                System.out.print(player1.getName() + " HAS WON!");
                System.exit(0);
            } else {
                System.out.print(player2.getName() + " HAS WON!");
                System.exit(0);
            }

        }

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

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
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

