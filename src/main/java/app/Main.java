package app;

import app.entities.CreatureCard;

import app.controllers.Game;

import java.io.IOException;
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
//        launch(args);

            List<CreatureCard> deck = new ArrayList();
            for(int i = 0; i < 20; i++) {
        CreatureCard card = new CreatureCard(10 , "Ali", 5, 2, 2, 2, FIRE, false);
                deck.add(card);
            }
        Game game = new Game(deck);
            game.start();
    }
}
