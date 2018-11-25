package app.network;

import app.controllers.Game;
import app.controllers.HighScore;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static java.lang.System.in;
import static java.lang.System.out;

public class ServerNetwork {
    private ServerSocket serverSocket;
    private Socket p1, p2;
    private PrintWriter outP1, outP2;
    private BufferedReader inP1, inP2;
    String player1Name;
    String player2Name;

    public ServerNetwork(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        try {
            out.println("Waiting for players to connect");
            p1 = serverSocket.accept();
            out.println("Player 1 connected");
            outP1 = new PrintWriter(p1.getOutputStream(), true);
            inP1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            player1Name = inP1.readLine();
            Game.sendHighScore(outP1, HighScore.showTopPlayers().toString());
            p2 = serverSocket.accept();
            out.println("Player 2 connected");
            outP2 = new PrintWriter(p2.getOutputStream(), true);
            inP2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            player2Name = inP2.readLine();
            Game.sendHighScore(outP2, HighScore.showTopPlayers().toString());
            out.println("Game has started");
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

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
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
