import java.util.Scanner;

public class Player {
    private int GRID_SIZE= new Game().getGRID_SIZE();
    private String playerName;
    private Scanner scanner = new Scanner(System.in);
    public Board playerGrid= new Board(GRID_SIZE);
    public Board playerTrackingGrid= new Board(GRID_SIZE);

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void playerTurn(Board opponentGrid) {
        System.out.print("Enter target (for example B1):");
        String target = scanner.next();
        Coordinate coordinate= new Coordinate();
        coordinate.stringToCoordinate(target);
        Utils utils=new Utils();
        if(utils.isValidInput(target)) {
            if (coordinate.isWater(playerTrackingGrid)){
                coordinate.missOrHit(opponentGrid,playerTrackingGrid);
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
