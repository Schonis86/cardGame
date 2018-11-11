package client;

import app.entities.CreatureCard;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientGame extends Thread {
    private ClientNetwork clientNetwork;
    private Scanner scanner;

    int turn;
    int round;
    String playerTurn;
    int player1Hp;
    int player2Hp;
    List<CreatureCard> player1CardsOnTable;
    List<CreatureCard> player2CardsOnTable;
    List<CreatureCard> cardsOnHand;
    ObjectMapper objectMapper;

    public ClientGame(String address, int port) throws IOException {
        this.clientNetwork = new ClientNetwork();
        clientNetwork.startConnection(address, port);
        scanner = new Scanner(System.in);
        objectMapper = new ObjectMapper();
        start();
    }

    public void start() {
        while (true) {
            String msgFromServer = null;
            try {
                msgFromServer = clientNetwork.getIn().readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!msgFromServer.startsWith("GUI")) {
                System.out.println(msgFromServer);
            } else {
                try {
                    deserializeMsgFromServer(msgFromServer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String msgToServer = scanner.nextLine();
            try {
                clientNetwork.sendMessage(msgToServer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deserializeMsgFromServer(String msg) throws IOException {
        String[] stringFromServer = msg.split(";");

        turn = Integer.parseInt(stringFromServer[2].replaceAll("\\s+", ""));
        round = Integer.parseInt(stringFromServer[3].replaceAll("\\s+", ""));
        playerTurn = stringFromServer[4].replaceAll("\\s+", "");
        player1Hp = Integer.parseInt(stringFromServer[5].replaceAll("\\s+", ""));
        player2Hp = Integer.parseInt(stringFromServer[6].replaceAll("\\s+", ""));

        String player1CardsOnTableString = stringFromServer[7];
        String player2CardsOnTableString = stringFromServer[8];
        String cardsOnHandString = stringFromServer[9];

        cardsOnHand = objectMapper.readValue(cardsOnHandString, new TypeReference<List<CreatureCard>>() {});
        player1CardsOnTable = objectMapper.readValue(player1CardsOnTableString, new TypeReference<List<CreatureCard>>() {});
        player2CardsOnTable = objectMapper.readValue(player2CardsOnTableString, new TypeReference<List<CreatureCard>>() {});

        cardsOnHand.forEach(c -> System.out.println(c.getName()));
        player1CardsOnTable.forEach(c -> System.out.println(c.getName()));
        player2CardsOnTable.forEach(c -> System.out.println(c.getName()));

    }

}
