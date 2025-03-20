import java.util.Scanner;

public class SpecialAttack {
    private boolean hasRunRadar = false;
    private  boolean hasRunMultiStrike = false;
    public static void radarScan(Board opponentGrid,Board trackingGrid) {
        int radarSize = 3;
        System.out.println("Radar Area: " + radarSize+" X "+radarSize);
        System.out.println("Enter target that you want scan it :");
        Scanner sc = new Scanner(System.in);
        String target = sc.next();
        if(Utils.isValidInput(target,trackingGrid.getSize())) {
            if(isValidInputForRadar(target,trackingGrid.getSize())) {
               Coordinate coordinate = new Coordinate(target);
               char[][] grid = trackingGrid.getGrid();
               char[][] enemyGrid= opponentGrid.getGrid();
               int row = coordinate.getRow();
               int col = coordinate.getCol();
               for(int i = -1 ; i < radarSize-1 ; i++) {
                   for(int j = -1 ; j < radarSize-1 ; j++) {
                       if (grid[row-i][col-j] == '~') {
                           if(enemyGrid[row-i][col-j]=='X'){
                               grid[row-i][col-j] = '*';
                           }
                       }
                   }
               }
               trackingGrid.printBoard();
                for(int i = -1 ; i < radarSize-1 ; i++) {
                    for(int j = -1 ; j < radarSize-1 ; j++) {
                        if(grid[row-i][col-j]=='*'){
                            grid[row-i][col-j] = '~';
                        }
                    }
                }
            }
            else {
                System.out.println("Invalid input, try again.");
                radarScan(opponentGrid,trackingGrid);
            }

        }
        else {
            System.out.println("Invalid input, please try again.");
            radarScan(opponentGrid,trackingGrid);
        }
    }

    public static void multiStrike(Board opponentGrid,Board trackingGrid ) {
        System.out.println("In this feature, by entering a coordinate,\n" +
                " that coordinate and its right and left sides will be targeted.");
        System.out.print("Enter coordinate :");
        Scanner sc = new Scanner(System.in);
        String target = sc.nextLine();
        int multiStrikeSize = 3;
        boolean isWater=true;
        if(Utils.isValidInput(target,trackingGrid.getSize())) {
            if(isValidInputForMultiStrike(target,trackingGrid.getSize())) {
                Coordinate coordinate = new Coordinate(target);
                char[][] grid = trackingGrid.getGrid();
                char[][] enemyGrid= opponentGrid.getGrid();
                int row = coordinate.getRow();
                int col = coordinate.getCol();
                for(int i = -1 ; i < multiStrikeSize-1 ; i++) {
                    if(grid[row][col+i]!='~'){
                        isWater=false;
                    }
                }
                if(isWater) {
                    for(int i = -1 ; i < multiStrikeSize-1 ; i++) {

                        if(enemyGrid[row][col+i]=='X') {
                            grid[row][col+i] = 'X';
                        }
                        else {
                            grid[row][col+i] = 'O';
                        }
                    }
                }
                else {
                    System.out.println("Invalid input, try again.");
                    multiStrike(opponentGrid,trackingGrid);
                }
            }
            else {
                System.out.println("Invalid input, try again.");
                multiStrike(opponentGrid,trackingGrid);
            }

        }
        else {
            System.out.println("Invalid input, please try again.");
            multiStrike(opponentGrid,trackingGrid);
        }
    }

    public boolean isHasRunRadar() {
        return hasRunRadar;
    }

    public void setHasRunRadar(boolean hasRunRadar) {
        this.hasRunRadar = hasRunRadar;
    }

    public  boolean isHasRunMultiStrike() {
        return hasRunMultiStrike;
    }

    public void setHasRunMultiStrike(boolean hasRunMultiStrike) {
        this.hasRunMultiStrike = hasRunMultiStrike;
    }
    private static  boolean isValidInputForRadar (String target, int gridSize) {
        Coordinate coordinate = new Coordinate(target);
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        if (row == 0 || row == gridSize || col == 0 || col == gridSize) {
            return false;
        }
        return true;
    }
    private static  boolean isValidInputForMultiStrike (String target, int gridSize) {
        Coordinate coordinate = new Coordinate(target);
        int col = coordinate.getCol();
        if (col == 0 || col == gridSize) {
            return false;
        }
        return true;
    }
}