package app.controllers;

import app.dto.GameDto;
import app.entities.CreatureCard;
import app.entities.Player;
import app.gui.Print;
import app.network.ServerNetwork;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Game {

    private ServerNetwork serverNetwork;
    private PrintWriter outP1, outP2;
    private BufferedReader inP1, inP2;

    private String[] msgFromClientArray;
    private String OPTION;
    private int CARD1, CARD2;

    private boolean player1Turn;
    private int roundCounter = 0;
    private int turnCounter = 0;
    private List<CreatureCard> allCards;
    private List<CreatureCard> player1Cards;
    private List<CreatureCard> player2Cards;
    private Player player1;
    private Player player2;

    private Player defendingPlayer, attackingPlayer;
    private PrintWriter outDefendingPlayer, outAttackingPlayer;
    private BufferedReader inDefendingPlayer, inAttackingPlayer;

    ObjectMapper objectMapper;

    public Game(List<CreatureCard> deck) throws IOException {
        this.allCards = deck;
        divideCards();
        player1 = new Player(player1Cards, "Jonas");
        player2 = new Player(player2Cards, "Robin");
        this.player1Turn = false;
        objectMapper = new ObjectMapper();
    }

    public void start() throws IOException {
        this.serverNetwork = new ServerNetwork(6666);
        serverNetwork.run();
        this.outP1 = serverNetwork.getOutP1();
        this.outP2 = serverNetwork.getOutP2();
        this.inP1 = serverNetwork.getInP1();
        this.inP2 = serverNetwork.getInP2();
        toggleTurn();
    }

    public void divideCards() {
        if (allCards != null) {
            Collections.shuffle(allCards);
            ArrayList<CreatureCard> p1List = new ArrayList<CreatureCard>(allCards.subList(0, allCards.size() / 2));
            ArrayList<CreatureCard> p2List = new ArrayList<CreatureCard>(allCards.subList(allCards.size() / 2, allCards.size()));
            setPlayer1Cards(p1List);
            setPlayer2Cards(p2List);
        }
    }

    public void toggleTurn() throws JsonProcessingException {
        setPlayer1Turn(!player1Turn);

        setTurnCounter(getTurnCounter() + 1);
        roundCheck();
        if (isPlayer1Turn()) {
            if (player1.getCardsOnHand().size() < 5) {
                player1.drawCard();
            }
        } else {
            if (player2.getCardsOnHand().size() < 5) {
                player2.drawCard();
            }
        }
        sendInfoAllPlayers();
        getUserInput();
    }

    public void getUserInput() throws JsonProcessingException {

        Boolean endTurn = false;
        if (isPlayer1Turn()) {
            attackingPlayer = player1;
            outAttackingPlayer = outP1;
            inAttackingPlayer = inP1;
            defendingPlayer = player2;
        } else {
            attackingPlayer = player2;
            outAttackingPlayer = outP2;
            inAttackingPlayer = inP2;
            defendingPlayer = player1;
        }
        attackingPlayer.setCardsToUnUsed();

        while (!endTurn) {
            try {
                Print.cardsVisibleForActivePlayer(attackingPlayer, defendingPlayer);
                outAttackingPlayer.println("your turn, what do you want to do ?");
                String msgFromClient = inAttackingPlayer.readLine();

                splitMsgFromClient(msgFromClient);
                //EXAMPLE:   msgFromClient = "ATTACK_CARD:1:2"   OPTION=ATTACK_CARD, CARD1=1, CARD2=2
                //EXAMPLE:   msgFromClient = "PLAY_CARD:3"       OPTION=PLAY_CARD, CARD=3
                switch (OPTION) {
                    case "PLAY_CARD":
                        attackingPlayer.playCard(CARD1);
                        break;
                    case "ATTACK_CARD":
                        CreatureCard attackingCard = attackingPlayer.getCardsOnTable().get(CARD1);
                        CreatureCard defendingCard = defendingPlayer.getCardsOnTable().get(CARD2);
                        attackCard(attackingCard, defendingCard);
                        break;
                    case "ATTACK_PLAYER":
                        CreatureCard creatureCard = attackingPlayer.getCardsOnTable().get(CARD1);
                        if (!creatureCard.getIsUsed()) {
                            attackPlayer(defendingPlayer, creatureCard.getAttackPoints());
                            attackingPlayer.getCardsOnTable().get(CARD1).setIsUsed(true);
                        } else {
                            throw new Exception("Card has already attacked this round !");
                        }
                        break;
                    case "END_TURN":
                        endTurn = true;
                        break;
                }
            } catch (Exception e) {
                Print.actionMessage(e.getMessage());
                outAttackingPlayer.println(e.getMessage());
            }
        }
        attackingPlayer.setHasPlayedCard(false);
        toggleTurn();
    }

    public void sendInfoAllPlayers() throws JsonProcessingException {
        GameDto gameDtoP1 = new GameDto(turnCounter, roundCounter, isPlayer1Turn(), player1.getHp(), player2.getHp(),
                player1.getCardsOnTable(), player2.getCardsOnTable(), player1.getCardsOnHand());

        GameDto gameDtoP2 = new GameDto(turnCounter, roundCounter, isPlayer1Turn(), player1.getHp(), player2.getHp(),
                player1.getCardsOnTable(), player2.getCardsOnTable(), player2.getCardsOnHand());

        String gameDtoP1String = objectMapper.writeValueAsString(gameDtoP1);
        String gameDtoP2String = objectMapper.writeValueAsString(gameDtoP2);

        outP1.println("GUI" + gameDtoP1String);
        outP2.println("GUI" + gameDtoP2String);
    }

    public void splitMsgFromClient(String msgFromClient) {
        msgFromClientArray = msgFromClient.split(":");
        OPTION = msgFromClientArray[0];
        CARD1 = msgFromClientArray.length > 1 ? Integer.parseInt(msgFromClientArray[1]) - 1 : -1;
        CARD2 = msgFromClientArray.length > 2 ? Integer.parseInt(msgFromClientArray[2]) - 1 : -1;
    }


    public boolean attackCard(CreatureCard attackingCard, CreatureCard defendingCard) throws Exception {
        if (roundCounter <= 1) {
            throw new Exception("Cant make attack move first round!");
        }
        if (attackingCard.getIsUsed()) {
            throw new Exception("Card has already attacked this round !");
        }
        CreatureCard player1Card;
        CreatureCard player2Card;
        int player1FightingPoints;
        int player2FightingPoints;
        boolean didPlayer1LoseAttack;

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
        Print.actionMessage(attackingCard.getName() + " HAS ATTACKED " + defendingCard.getName());
        if (fightResult < 0) {
            player1Card.decreaseHp(-fightResult);
            didPlayer1LoseAttack = true;
        } else {
            player2Card.decreaseHp(fightResult);
            didPlayer1LoseAttack = false;
        }
        player1.removeCardIfDead();
        player2.removeCardIfDead();
        return didPlayer1LoseAttack;
    }

    public void attackPlayer(Player player, int attackNumber) throws Exception {
        if (roundCounter <= 1) {
            throw new Exception("Cant make attack move first round!");
        }
        if (player.getCardsOnTable().size() != 0) {
            throw new Exception("Can't attack player with cards on table!");
        }
        player.reduceHp(attackNumber);
        Print.actionMessage((player.getName() + " " + "took " + attackNumber + " damage!"));
        if (isPlayerDead(player)) {
            Print.actionMessage(player.getName() + " died!");
            if (player1Turn) {
                Print.actionMessage(player1.getName() + " won!");
            } else {
                Print.actionMessage(player2.getName() + " won!");
            }
            System.exit(0);
        }
    }

    public boolean isPlayerOutOfCards(Player player){
        return(player.getCardsInDeck().size()==0 && player.getCardsOnHand().size()==0 && player.getCardsOnTable().size()==0);
    }

    public Boolean isPlayerDead(Player player) {
        return player == null || player.getHp() <= 0;
    }

    public void roundCheck() {
        if (turnCounter % 2 != 0) {
            roundCounter++;
        }
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

    public void setPlayer1Cards(List<CreatureCard> player1Cards) {
        this.player1Cards = player1Cards;
    }

    public void setPlayer2Cards(List<CreatureCard> player2Cards) {
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

    public List<CreatureCard> getAllCards() {
        return allCards;
    }

    public List<CreatureCard> getPlayer1Cards() {
        return player1Cards;
    }

    public List<CreatureCard> getPlayer2Cards() {
        return player2Cards;
    }
}

