package client;

import app.dto.GameDto;
import app.entities.CreatureCard;
import client.component.gameBoard.GameBoardController;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ClientGame extends Thread {

    public static ClientNetwork clientNetwork;

    public static ClientNetwork getClientNetwork () {
        return clientNetwork;
    }

    static String player;
    int turn;
    int round;
    String playerTurn;
    static int player1Hp;
    static int player2Hp;
    List<CreatureCard> player1CardsOnTable;
    List<CreatureCard> player2CardsOnTable;
    List<CreatureCard> cardsOnHand;
    ObjectMapper objectMapper;
    Scanner scanner;
    static GameDto gameData;

    GameBoardController controller;

    public ClientGame(String address, int port, GameBoardController controller) throws IOException {
        this.controller = controller;
        this.clientNetwork = new ClientNetwork();
        clientNetwork.startConnection(address, port);
        objectMapper = new ObjectMapper();
        scanner = new Scanner(System.in);
        receiveMsg.start();
        sendMsgLoopTemp();
    }

    private Thread receiveMsg = new Thread() {
        public void run() {
            while (true) {
                String msgFromServer = null;
                try {
                    msgFromServer = clientNetwork.getIn().readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(msgFromServer.startsWith("PLAYER")){
                    String[] playerString = msgFromServer.split(":");
                    player = playerString[1];
                    System.out.println("YOU ARE PLAYER: " + player);
                }
                else if (!msgFromServer.startsWith("GUI")) {
                    System.out.println(msgFromServer);
                } else {
                    try {
                        deserializeMsgFromServer(msgFromServer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    public void sendMsgLoopTemp() {
        while (true) {
            String msgToServer = scanner.nextLine();
            clientNetwork.getOut().println(msgToServer);
        }
    }

    public void deserializeMsgFromServer(String msg) throws IOException {
        String parsedString = msg.replace("GUI", "");
        GameDto gameDto = objectMapper.readValue(parsedString, GameDto.class);
        gameData = gameDto;
        player2Hp = gameDto.getPlayer2Hp();
        player1Hp = gameDto.getPlayer1Hp();

        controller.update();

        //gameDto contains all information about the game example getCardsOnhand:
        //  gameDto.getCardsOnHand().forEach(c -> System.out.println(c.getName()));
    }

    public static GameDto getDto() {
        return gameData;
    }
    public static String getPlayer() {return player; }

}
