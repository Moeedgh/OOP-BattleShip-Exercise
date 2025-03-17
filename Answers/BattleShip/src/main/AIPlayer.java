import java.util.Random;
import java.util.Scanner;

public class AIPlayer extends Player {
    private int size;
    private boolean horizontal;
    public AIPlayer(String name,int size) {
        super(name,size);
        this.size = size;
    }
    public String makeMove(Board trackingGrid) {
        Random rand = new Random();
        char[][] grid = trackingGrid.getGrid();
        boolean horizontal=false;
        int i=0,j=0;
//        while (true){
//            if(grid[i][j] == 'X') {
//                switch (grid[i][j+1]){
//                    case 'X':
//                        horizontal = true;
//                    case 'O':
//
//
//
//                }
//            }
//
//
//            i++;
//            j++;
//        }
        return "" + (char) ('A' + rand.nextInt(size)) + rand.nextInt(size);

    }
    public void aiTurn(Board opponentGrid) {
        String target = makeMove(playerTrackingGrid);
        Coordinate coordinate= new Coordinate(target);
        if(Utils.isValidInput(target)) {
            if (coordinate.isWater(playerTrackingGrid)){// if hit , player shot again.
                if(coordinate.attack(opponentGrid,playerTrackingGrid)){
                    if(opponentGrid.allShipSunk()){
                        return;
                    }
                    else {
                        System.out.println("AI's turn:");
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
