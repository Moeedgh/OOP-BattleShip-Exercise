import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Utils {

    public static boolean isValidInput(String input) {
        if (input.length() != 2) return false;
        char col = input.charAt(0);
        char row = input.charAt(1);
        return (col >= 'A' && col <= 'J') && (row >= '0' && row <= '9');
    }
    public static void placeShipRandomly(Player player){// Utils
        for (Ship ship : player.playerGrid.getShips()) {
            ShipPlacer.placeShipRandomly(player.playerGrid,ship);
        }
    }
    public static void placeShipManually(Player player){//Utils
        for (Ship ship : player.playerGrid.getShips()) {
            ShipPlacer.placeShipManually(player.playerGrid,ship);
        }
    }
    public static void shipsInfo(Board board){
        Scanner input = new Scanner(System.in);
        int[] countShips= new int[6];
        countShips=Utils.countShips(board.getShips());
        System.out.println("*********** Pay attention **********");
        System.out.print("Ship Size :");
        System.out.printf("%25s", "Number of Ships :");
        System.out.println();
        for (int i = 2; i < countShips.length; i++) {
            if (countShips[i] ==0)
                continue;
            System.out.printf(Locale.ENGLISH,"%10d",i);
            System.out.printf(Locale.ENGLISH,"%25d",countShips[i]);
            System.out.println();
        }
        System.out.println("************************************");
        input.nextLine();
    }
    private static int[] countShips(ArrayList<Ship> ships){
        int[] countShips = {0,0,0,0,0,0};
        for (Ship ship : ships) {
            countShips[ship.getSize()]++;
        }
        return countShips;
    }

}

