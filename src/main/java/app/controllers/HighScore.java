package app.controllers;

import app.entities.CreatureCard;
import app.entities.Player;
import com.mysql.cj.conf.ConnectionUrlParser;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class HighScore {

    public static void addPlayers(Player player1, Player player2){
    try {
        String myDriver = "com.mysql.cj.jdbc.Driver";

        String myUrl = "jdbc:mysql://sql7.freemysqlhosting.net/sql7265239";

        Class.forName(myDriver);

        Connection conn = DriverManager.getConnection(myUrl, "sql7265239", "cmhKZhQUGY");

        String query1 = "INSERT INTO highScore (Name, Points) VALUES('" + player1.getName() +"','" + (player1.getPoints()) + "')";
        String query2 = "INSERT INTO highScore (Name, Points) VALUES('" + player2.getName() +"','" + (player2.getPoints()) + "')";

        Statement st = conn.createStatement();

        st.executeUpdate(query1);
        st.executeUpdate(query2);

        st.close();


    }
        catch(Exception e)
    {
        System.err.println("Got an exception! ");
        System.err.println(e.getMessage());
    }}
        public static void showTopPlayers(int numberOfPlayers){
            List<Pair> highScore = new ArrayList<>();
            Pair<String, Integer> player;

            try {
                String myDriver = "com.mysql.cj.jdbc.Driver";

                String myUrl = "jdbc:mysql://sql7.freemysqlhosting.net/sql7265239";

                Class.forName(myDriver);

                Connection conn = DriverManager.getConnection(myUrl, "sql7265239", "cmhKZhQUGY");

                String query = "SELECT * FROM highScore";

                Statement st = conn.createStatement();

                ResultSet rs = st.executeQuery(query);

                while(rs.next()){
                    String name = rs.getString("Name");
                    int points = rs.getInt("Points");
                    player = new Pair<>(name, points);
                    highScore.add(player);
                }

                st.close();
            }
            catch(Exception e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
            Collections.sort(highScore, HighScore.sort);

            int numberToShow;
            if(highScore.size()<numberOfPlayers) {numberToShow = highScore.size();}
            else {numberToShow = numberOfPlayers;}

            System.out.println("Plc: Namn:   Poäng:");
            for (int i = 0; i <numberToShow ; i++) {
                System.out.println((i+1) + "     "+highScore.get(i).getKey() + "   " + highScore.get(i).getValue());

            }}
    public static final Comparator<Pair> sort = new Comparator<Pair>(){
    public int compare(Pair p1, Pair p2){
                return (Integer)p2.getValue()-(Integer)p1.getValue();
};};

}
