import java.util.Random;
import java.util.Scanner;

public class ShipPlacer {
    public static void placeShipRandomly(Board board, Ship ship) {
        Random rand = new Random();
        boolean placed = false;
        int row;
        int col;
        boolean horizontal;
        while (!placed) {
            row = rand.nextInt(board.getSize());
            col = rand.nextInt(board.getSize());
            horizontal = rand.nextBoolean();
            placed = board.placeShip(ship, row, col, horizontal);
        }
    }
    public static void placeShipManually(Board board, Ship ship) {
        Scanner scanner = new Scanner(System.in);
        boolean horizontal;
        Coordinate coordinate;
        System.out.println("Your Board :");
        board.printBoard();
        System.out.println("\nShip's size :"+ship.getSize());
        do {
            System.out.println("Please enter a valid coordinate that you want to place ship:");
            String coord = scanner.nextLine();
            if (Utils.isValidInput(coord,board.getSize())) {
                coordinate = new Coordinate(coord);
                if (coordinate.isWater(board)) {
                    break;
                }
                else {
                    System.out.println("This coordinate is not a water, please try again.");
                }
            }
            else{
                System.out.println("Invalid coordinate, please try again.");
            }
        }while (true);

        System.out.println("************************** Pay Attention **************************");
        System.out.println("In placing the ship vertically, the ship is positioned facing down");
        System.out.println("In placing the ship horizontally, the ship is positioned facing right");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        do {
            System.out.println("PLace Ship horizontal or vertical ? ( 'H' for place horizontal and 'V' for place vertical ) ");
            char inputChar = scanner.nextLine().charAt(0);
            if (inputChar == 'H') {
                horizontal = true;
                break;
            }
            else{
                if (inputChar == 'V') {
                    horizontal = false;
                    break;
                }
                else{
                    System.out.println("Invalid character, please try again.");
                }
            }
        }while (true);
        boolean placed = board.placeShip(ship,coordinate.getRow(),coordinate.getCol(),horizontal);
        if (placed) {
            System.out.println("Ship Placed successfully.");
        }
        else {
            System.out.println("!!!!! Error placing ship !!!!!.");
            System.out.println("Can't place ship, please try again.");
            placeShipManually(board, ship);
        }
    }
}
