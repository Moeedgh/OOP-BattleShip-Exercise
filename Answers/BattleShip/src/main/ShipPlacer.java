import java.util.Random;

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

    }
}
