import java.util.Scanner;

public class Player {
    private int GRID_SIZE;
    private final String playerName;
    private Scanner scanner = new Scanner(System.in);
    public Board playerGrid;
    public Board playerTrackingGrid;

    public Player(String playerName, int gridSize) {
        this.playerName = playerName;
        GRID_SIZE = gridSize;
        playerGrid = new Board(GRID_SIZE);
        playerTrackingGrid = new Board(GRID_SIZE);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void playerTurn(Board opponentGrid) {
        System.out.print("Enter target (for example B1):");
        String target = scanner.next();
        Coordinate coordinate= new Coordinate(target);
        if(Utils.isValidInput(target,GRID_SIZE)) {
            if (coordinate.isWater(playerTrackingGrid)){// if hit , player shot again.
                if(coordinate.attack(opponentGrid,playerTrackingGrid)){
                    if(opponentGrid.allShipSunk()){
                        return;
                    }
                    else {
                        System.out.println(playerName+"'s turn:");
                        playerTrackingGrid.printBoard();
                        playerTurn(opponentGrid);
                    }
                }

            }
            else {
                System.out.println("These coordinates have been entered, please enter a valid coordinate.");
                playerTurn(opponentGrid);
            }
        }
        else {
            System.out.println("Invalid input, try again");
            playerTurn(opponentGrid);
        }
    }
}
