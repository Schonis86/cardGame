package app.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.in;
import static java.lang.System.out;

public class ServerNetwork {
    private ServerSocket serverSocket;
    private Socket p1, p2;
    private PrintWriter outP1, outP2;
    private BufferedReader inP1, inP2;

    public ServerNetwork(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        try {
            out.println("Waiting for players to connect");
            p1 = serverSocket.accept();
            out.println("Player 1 connected");
            p2 = serverSocket.accept();
            out.println("Player 2 connected");
            out.println("Game has started");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outP1 = new PrintWriter(p1.getOutputStream(),true);
            outP2 = new PrintWriter(p2.getOutputStream(),true);
            inP1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            inP2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stopGame() throws IOException {
        in.close();
        out.close();
        p1.close();
        p2.close();
        serverSocket.close();
    }

    public PrintWriter getOutP1() {
        return outP1;
    }

    public void setOutP1(PrintWriter outP1) {
        this.outP1 = outP1;
    }

    public PrintWriter getOutP2() {
        return outP2;
    }

    public void setOutP2(PrintWriter outP2) {
        this.outP2 = outP2;
    }

    public BufferedReader getInP1() {
        return inP1;
    }

    public void setInP1(BufferedReader inP1) {
        this.inP1 = inP1;
    }

    public BufferedReader getInP2() {
        return inP2;
    }

    public void setInP2(BufferedReader inP2) {
        this.inP2 = inP2;
    }
}
