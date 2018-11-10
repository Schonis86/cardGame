package client;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        ClientGame clientGame = new ClientGame("localhost", 6666);
        clientGame.start();
    }
}
