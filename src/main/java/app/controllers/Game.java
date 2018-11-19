package app.controllers;

import app.dto.GameDto;
import app.entities.CreatureCard;
import app.entities.Magic;
import app.entities.MagicCard;
import app.entities.GameCard;
import app.entities.MagicCard;

import app.entities.Player;
import app.gui.Print;
import app.network.ServerNetwork;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static app.MagicMethod.*;

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
    private List<GameCard> allCards;
    private List<GameCard> player1Cards;
    private List<GameCard> player2Cards;
    private Player player1;
    private Player player2;

    Magic magic;

    private Player defendingPlayer, attackingPlayer;
    private PrintWriter outDefendingPlayer, outAttackingPlayer;
    private BufferedReader inDefendingPlayer, inAttackingPlayer;

    ObjectMapper objectMapper;

    public Game(List<GameCard> deck) throws IOException {
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
        outP1.println("PLAYER:player1");
        outP2.println("PLAYER:player2");
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
        getUserInput();
    }

    public void getUserInput() throws JsonProcessingException {
        sendInfoAllPlayers();
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
        CreatureCard defendingCard;
        CreatureCard attackingCard;
        MagicCard magicCard;

        while (!endTurn) {
            try {
                Print.cardsVisibleForActivePlayer(attackingPlayer, defendingPlayer);
                outAttackingPlayer.println("MESSAGE:your turn, what do you want to do ?");
                String msgFromClient = inAttackingPlayer.readLine();

                splitMsgFromClient(msgFromClient);
                switch (OPTION) {
                    case "PLAY_CARD":
                        attackingPlayer.playCard(CARD1);
                        break;
                    case "ATTACK_CARD":
                        attackingCard = attackingPlayer.getCardsOnTable().get(CARD1);
                        defendingCard = defendingPlayer.getCardsOnTable().get(CARD2);
                        attackCard(attackingCard, defendingCard);
                        break;
                    case "ATTACK_PLAYER":
                        CreatureCard creatureCard = attackingPlayer.getCardsOnTable().get(CARD1);
                        if (!creatureCard.getIsUsed()) {
                            attackPlayer(defendingPlayer, creatureCard.getAttackPoints());
                            attackingPlayer.getCardsOnTable().get(CARD1).setIsUsed(true);
                        }
                        break;
                    case "CAST_MAGIC_INSTANT":
                        magicCard = (MagicCard) attackingPlayer.getCardsOnHand().get(CARD1);
                        castMagicMethod(magicCard);
                    case "CAST_MAGIC_TARGET_DAMAGE":
                        magicCard = (MagicCard) attackingPlayer.getCardsOnHand().get(CARD1);
                        defendingCard = defendingPlayer.getCardsOnTable().get(CARD2);
                        castMagicMethod(magicCard, defendingCard);
                    case "CAST_MAGIC_TARGET_HEAL":
                        magicCard = (MagicCard) attackingPlayer.getCardsOnHand().get(CARD1);
                        defendingCard = attackingPlayer.getCardsOnTable().get(CARD2);
                        castMagicMethod(magicCard, defendingCard);
                    case "END_TURN":
                        endTurn = true;
                        break;
                }
            } catch (Exception e) {
                outAttackingPlayer.println("ERROR:" + e.getMessage());
            }
            checkDeath(player1);
            checkDeath(player2);
            sendInfoAllPlayers();
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

        outP1.println("GUI:" + gameDtoP1String);
        outP2.println("GUI:" + gameDtoP2String);
    }

    public void sendMessageAllPlayers(String msg) {
        Print.actionMessage(msg);
        outP1.println("MESSAGE:" + msg);
        outP2.println("MESSAGE:" + msg);
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
        sendMessageAllPlayers(attackingCard.getName() + " HAS ATTACKED " + defendingCard.getName());

        if (fightResult < 0) {
            player1Card.decreaseHp(-fightResult);
            didPlayer1LoseAttack = true;
        } else {
            player2Card.decreaseHp(fightResult);
            didPlayer1LoseAttack = false;
        }
        player1.removeCardIfDead();
        player2.removeCardIfDead();
        checkDeath(player1);
        checkDeath(player2);
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
        sendMessageAllPlayers((player.getName() + " " + "took " + attackNumber + " damage!"));
        checkDeath(player);
    }

    public void checkDeath(Player player) {
        if (isPlayerDead(player)) {
            sendMessageAllPlayers(player.getName() + " died!");
            if (player1Turn) {
                sendMessageAllPlayers(player1.getName() + " won!");
            } else {
                sendMessageAllPlayers(player2.getName() + " won!");
            }
            System.exit(0);
        }
    }

    public boolean isPlayerOutOfCards(Player player) {
        return (player.getCardsInDeck().size() == 0 && player.getCardsOnHand().size() == 0 && player.getCardsOnTable().size() == 0);
    }

    //Omedelbara effekter
    public void castMagicMethod(MagicCard magicCard) {

        switch (magicCard.getMagicMethod()) {
            case HEALPLAYER:
                magic.selfHealPlayer(attackingPlayer, 2);
                break;

            case DAMAGEPLAYER:
                magic.damageEnemyPlayer(defendingPlayer, 2);
                break;

            case HEALALLCARDS:
                magic.healFriendlyCards(attackingPlayer.getCardsOnTable(), 2);
                break;

            case DAMAGEALLCARDS:
                magic.damageEnemyCards(defendingPlayer.getCardsOnTable(), 2);
                break;
        }
    }

    // Riktade effekter
    public void castMagicMethod(MagicCard magicCard, CreatureCard creatureCard) {

        switch (magicCard.getMagicMethod()) {
            case DAMAGECARD:
                magic.damageOneCard(creatureCard, 2);
                break;

            case HEALCARD:
                magic.healOneCard(creatureCard, 2);
                break;
        }
    }

    public Boolean isPlayerDead(Player player) {
        return player == null || player.getHp() <= 0 || isPlayerOutOfCards(player);
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

