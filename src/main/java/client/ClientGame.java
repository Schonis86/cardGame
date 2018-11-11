package client;

import app.dto.GameDto;
import app.entities.CreatureCard;
import app.gui.Print;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ClientGame extends Thread {
    public ClientNetwork clientNetwork;

    int turn;
    int round;
    String playerTurn;
    int player1Hp;
    int player2Hp;
    List<CreatureCard> player1CardsOnTable;
    List<CreatureCard> player2CardsOnTable;
    List<CreatureCard> cardsOnHand;
    ObjectMapper objectMapper;
    Scanner scanner;

    public ClientGame(String address, int port) throws IOException {
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
                if (!msgFromServer.startsWith("GUI")) {
                    Print.actionMessage(msgFromServer);
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

        //gameDto contains all information about the game example getCardsOnhand:
      //  gameDto.getCardsOnHand().forEach(c -> System.out.println(c.getName()));
    }

}
