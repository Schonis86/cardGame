package app;

import app.entities.CreatureCard;

import app.controllers.Game;
import app.entities.GameCard;
import app.entities.MagicCard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static app.AttackType.FIRE;

public class Main{

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }



    public static void main(String[] args) throws IOException {
        List<GameCard> deck = new ArrayList();
//        for(int i = 0; i < 20; i++) {
//            CreatureCard card = new CreatureCard("kort" + (i+1));
//            deck.add(card);
//        }


        try {
            // create our mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/cardgame_tdd?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            //String myUrl = "jdbc:mysql://localhost:3306/cardgame_tdd";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "linasdatabas");

            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM gamecards";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int hp = rs.getInt("hp");
                int energyCost = rs.getInt("energyCost");
                int attackPoints = rs.getInt("attackPoints");
                int defencePoints = rs.getInt("defencePoints");
                int coolDown = rs.getInt("coolDown");
                AttackType attackType = (AttackType.valueOf(rs.getString("attackType")));
                //int isUsed = rs.getInt("isUsed");
                String imageUrl = rs.getString("imageUrl");
                String specialAbility = rs.getString("specialAbility");
                if (hp != 0) {
                    deck.add(new CreatureCard(id, name, energyCost, attackPoints, defencePoints, coolDown, attackType, false));
                } else if (hp == 0) {
                    deck.add(new MagicCard());
                }
                // print the results
                System.out.format("%s, %s\n", id, name);
//        launch(args);

//                List<GameCard> deck = new ArrayList();
//                for (int i = 0; i < 20; i++) {
//                    CreatureCard card = new CreatureCard(10, "Ali", 5, 2, 2, 2, FIRE, false);
//                    deck.add(card);
//                }
                st.close();
                Game game = new Game(deck);
            }}
        catch(Exception e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }



    }}