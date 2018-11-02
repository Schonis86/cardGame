package app.gui;

import app.entities.GameCard;
import app.entities.Player;

import java.util.List;

public class Print {

    public static void actionMessage(String message) {
        System.out.println("********************** " + message.toUpperCase() + " **********************"); //Calle
    }

    public static void optionList(List<GameCard> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i + 1) + ": " + list.get(i).getName() + " hp:" + list.get(i).getHp() + "  ");
        }
    }

    public static void cardsVisibleForActivePlayer(Player attackingPlayer, Player defendingPlayer) {
        System.out.println("********************************************************************");
        actionMessage("     " + attackingPlayer.getName() + " turn       ");
        System.out.println("********************************************************************");

        actionMessage(" Enemy Cards On Table ");
        if (defendingPlayer.getCardsOnTable().size() == 0) {
            System.out.println("*                                                                  *");
        } else {
            defendingPlayer.getCardsOnTable().forEach(c -> System.out.print(c.getName() + " hp:" + c.getHp() + " "));
            System.out.println(" ");
        }
        actionMessage("  Your Cards On Table ");
        if (attackingPlayer.getCardsOnTable().size() == 0) {
            System.out.println("*                                                                  *");
        } else {
            attackingPlayer.getCardsOnTable().forEach(c -> System.out.print(c.getName() + " hp:" + c.getHp() + "  "));
            System.out.println(" ");
        }
        actionMessage("  Your Cards On Hand  ");
        if (attackingPlayer.getCardsOnHand().size() == 0) {
            System.out.println("*                                                                *");
        } else {
            attackingPlayer.getCardsOnHand().forEach(c -> System.out.print("|" + c.getName() + " hp:" + c.getHp() + "| "));
        }
        System.out.println("\n********************************************************************");

    }
}
