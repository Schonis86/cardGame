package client;

import java.io.IOException;
import java.util.Scanner;

public class ClientGame extends Thread {
    private ClientNetwork clientNetwork;
    private Scanner scanner;

    public ClientGame(String address, int port) throws IOException {
        this.clientNetwork = new ClientNetwork();
        clientNetwork.startConnection(address, port);
        scanner = new Scanner(System.in);
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
            System.out.println(msgFromServer);
            String msgToServer = scanner.nextLine();
            try {
                clientNetwork.sendMessage(msgToServer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
