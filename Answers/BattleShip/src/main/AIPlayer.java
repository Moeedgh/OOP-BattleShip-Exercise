import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AIPlayer extends Player {
    private int gridSize;
    private List<String> hitCoordinates = new ArrayList<>();
    private List<String> potentialTargets = new ArrayList<>();
    private List<String> missedCoordinates = new ArrayList<>();

    public AIPlayer(String name, int size) {
        super(name, size);
        this.gridSize = size;
    }

    public String makeMove() {

        if (!hitCoordinates.isEmpty()) {
            return targetShip();
        } else {

            return randomMove();
        }
    }

    private String randomMove() {
        Random rand = new Random();
        String move;
        do {
            move = "" + (char) ('A' + rand.nextInt(gridSize)) + rand.nextInt(gridSize);
        } while (missedCoordinates.contains(move));
        return move;
    }

    private String targetShip() {
        if (potentialTargets.isEmpty()) {
            String lastHit = hitCoordinates.getLast();
            if(lastHit.length()==2){
                int row = lastHit.charAt(1) - '0';
                int col = lastHit.charAt(0) - 'A';


                if (row > 0) potentialTargets.add("" + (char) ('A' + col) + (row - 1));
                if (row < gridSize - 1) potentialTargets.add("" + (char) ('A' + col) + (row + 1));
                if (col > 0) potentialTargets.add("" + (char) ('A' + (col - 1)) + row);
                if (col < gridSize - 1) potentialTargets.add("" + (char) ('A' + (col + 1)) + row);
            }
            else{
                String Row = lastHit.substring(1,3);
                int row = Integer.parseInt(Row);
                int col = lastHit.charAt(0) - 'A';


                if (row > 0) potentialTargets.add("" + (char) ('A' + col) + (row - 1));
                if (row < gridSize - 1) potentialTargets.add("" + (char) ('A' + col) + (row + 1));
                if (col > 0) potentialTargets.add("" + (char) ('A' + (col - 1)) + row);
                if (col < gridSize - 1) potentialTargets.add("" + (char) ('A' + (col + 1)) + row);
            }
        }

        String target;
        do {
            target = potentialTargets.removeFirst();
        } while (missedCoordinates.contains(target));
        return target;
    }

    private void recordHit(String coordinate) {
        hitCoordinates.add(coordinate);
    }

    private void recordMiss(String coordinate) {
        missedCoordinates.add(coordinate);
        potentialTargets.remove(coordinate);
    }

    public void aiTurn(Board opponentGrid) {
        Scanner scanner = new Scanner(System.in);
        String target = makeMove();
        boolean shipSunk = false;
        if (Utils.isValidInput(target,gridSize)) {
            Coordinate coordinate = new Coordinate(target);
            if (coordinate.isWater(playerTrackingGrid)) {
                if (coordinate.attack(opponentGrid, playerTrackingGrid)) {
                    for (Ship ship : opponentGrid.getShips()) {
                        if (ship.isShipSunk()) {
                            shipSunk = true;
                            missedCoordinates.addAll(hitCoordinates);
                            hitCoordinates.clear();
                            potentialTargets.clear();
                        }
                    }
                    if (!shipSunk) {
                        recordHit(target);
                    }
                    scanner.nextLine();
                    if (opponentGrid.allShipSunk()) {
                        return;
                    }
                    else {
                        System.out.println("AI's turn:");
                        playerTrackingGrid.printBoard();
                        aiTurn(opponentGrid);
                    }
                } else {
                    recordMiss(target);
                    scanner.nextLine();
                }
            } else {
                recordMiss(target);
                aiTurn(opponentGrid);
            }
        } else {
            aiTurn(opponentGrid);
        }
        scanner.close();
    }
}
