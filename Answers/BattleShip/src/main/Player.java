import java.util.Scanner;

public class Player {
    private int GRID_SIZE;
    private final String playerName;
    private Scanner scanner = new Scanner(System.in);
    private Scanner SPscanner = new Scanner(System.in);
    private SpecialAttack specialAttack = new SpecialAttack();
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

    public void playerTurn(Board opponentGrid, boolean advancedMode) {
        if(advancedMode && (!specialAttack.isHasRunMultiStrike() || !specialAttack.isHasRunRadar())) {
            if(!specialAttack.isHasRunRadar()){
                System.out.println("If you want use Radar Scan enter 'R' .");
            }
            if (!specialAttack.isHasRunMultiStrike()){
                System.out.println("If you want use Multi Strike enter 'M' .");
            }

            System.out.println("If you don't want use Special Attack, press Enter.");
            char inputChar = ' ';
            do{

                String input = SPscanner.nextLine();
                if(input.isBlank()){
                    break;
                }
                inputChar = input.charAt(0);
                if ((inputChar == 'R' || inputChar == 'r') && !specialAttack.isHasRunRadar()) {
                    break;
                }
                if((inputChar == 'M' || inputChar == 'm') && !specialAttack.isHasRunMultiStrike() ){
                    break;
                }
                System.out.print("invalid input, please try again:");

            }while (true);

            if(inputChar == 'R' || inputChar == 'r'){
                System.out.println("************ RADAR SCAN *************");
                SpecialAttack.radarScan(opponentGrid, playerTrackingGrid);
                specialAttack.setHasRunRadar(true);
                return;
            }
            if(inputChar == 'M' || inputChar == 'm'){
                System.out.println("************ MULTI STRIKE *************");
                SpecialAttack.multiStrike(opponentGrid, playerTrackingGrid);
                playerTrackingGrid.printBoard();
                specialAttack.setHasRunMultiStrike(true);
                return;
            }
        }
        System.out.print("Enter target (for example B1):");
        String target = scanner.next();
        if(Utils.isValidInput(target,GRID_SIZE)) {
            Coordinate coordinate= new Coordinate(target);
            if (coordinate.isWater(playerTrackingGrid)){// if hit , player shot again.
                if(coordinate.attack(opponentGrid,playerTrackingGrid)){
                    if(opponentGrid.allShipSunk()){
                        return;
                    }
                    else {
                        System.out.println(playerName+"'s turn:");
                        playerTrackingGrid.printBoard();
                        playerTurn(opponentGrid,advancedMode);
                    }
                }

            }
            else {
                System.out.println("These coordinates have been entered, please enter a valid coordinate.");
                playerTurn(opponentGrid,advancedMode);
            }
        }
        else {
            System.out.println("Invalid input, try again");
            playerTurn(opponentGrid,advancedMode);
        }
    }
}
