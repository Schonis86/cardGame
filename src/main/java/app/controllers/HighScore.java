package app.controllers;

import app.entities.CreatureCard;
import app.entities.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HighScore {
    public static void addPlayers(Player player1, Player player2){
    try {
        String myDriver = "com.mysql.cj.jdbc.Driver";

        String myUrl = "jdbc:mysql://sql7.freemysqlhosting.net/sql7265239";

        Class.forName(myDriver);

        Connection conn = DriverManager.getConnection(myUrl, "sql7265239", "cmhKZhQUGY");

        String query = "INSERT INTO high score (Name, Points) VALUES" + player1.getName() +"," + player1.getHp()+ "(Name, Points) VALUES)" + player2.getName() +"," + player2.getHp();

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery(query);

        st.close();


    }
        catch(Exception e)
    {
        System.err.println("Got an exception! ");
        System.err.println(e.getMessage());
    }}
        public static void showTopPlayers(int numberOfPlayers){
            try {
                String myDriver = "com.mysql.cj.jdbc.Driver";

                String myUrl = "jdbc:mysql://sql7.freemysqlhosting.net/sql7265239";

                Class.forName(myDriver);

                Connection conn = DriverManager.getConnection(myUrl, "sql7265239", "cmhKZhQUGY");

                String query = "SELECT * FROM high score";

                Statement st = conn.createStatement();

                ResultSet rs = st.executeQuery(query);

                st.close();


            }
            catch(Exception e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }

}}
