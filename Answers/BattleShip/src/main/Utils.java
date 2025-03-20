import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Utils {

    public static boolean isValidInput(String input,int gridSize) {
        char col;
        char row;
        String Row;
        if(input.isBlank()){
            return false;
        }
        switch (input.length()) {
            case 2:
                col = input.charAt(0);
                row = input.charAt(1);
                if(!Character.isDigit(row)){
                    return false;
                }
                return (col >= 'A' && col <= (char)('A'+gridSize-1)) && (row >= '0' && row <= (char)('0'+gridSize-1));
            case 3:
                col = input.charAt(0);
                Row = input.substring(1,3);
                for (char c : Row.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        return false;
                    }
                }
                int x = Integer.parseInt(Row);
                return (col >= 'A' && col <= (char)('A'+gridSize-1)) && (x >= 0 && x < gridSize);
            default:
                return false;
        }

    }
    public static void placeShipRandomly(Player player){// Utils
        for (Ship ship : player.playerGrid.getShips()) {
            ShipPlacer.placeShipRandomly(player.playerGrid,ship);
        }
    }
    public static void placeShipManually(Player player){//Utils
        System.out.println("********* PLACING SHIP MANUALLY *********");
        Utils.shipsInfo(player.playerGrid);
        for (Ship ship : player.playerGrid.getShips()) {
            ShipPlacer.placeShipManually(player.playerGrid,ship);
        }
    }
    public static void shipsInfo(Board board){
        Scanner input = new Scanner(System.in);
        int[] countShips= new int[6];
        countShips=Utils.countShips(board.getShips());
        System.out.println("*********** Pay Attention **********");
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
        input.close();
    }
    private static int[] countShips(ArrayList<Ship> ships){
        int[] countShips = {0,0,0,0,0,0};
        for (Ship ship : ships) {
            countShips[ship.getSize()]++;
        }
        return countShips;
    }

}

