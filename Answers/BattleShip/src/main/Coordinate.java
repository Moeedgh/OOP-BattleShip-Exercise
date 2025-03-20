public class Coordinate {
    private int row;
    private int col;
    public Coordinate(String target) {
        char colChar;
        char rowChar;
        if (target.length() == 2) {
            colChar = target.charAt(0);
            rowChar = target.charAt(1);
            this.row = rowChar - '0';
            this.col = colChar - 'A';
        } else {
            colChar = target.charAt(0);
            this.col = colChar - 'A';
            String Row = target.substring(1, 3);
            this.row = Integer.parseInt(Row);
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public boolean attack(Board opponentGrid, Board trackingGrid) {
        char[][] opponetBoard=opponentGrid.getGrid();
        char[][] trackingBoard=trackingGrid.getGrid();
        if(opponetBoard[row][col]=='X') {
            trackingBoard[row][col]='X';
            System.out.println("Hit!");
            for (Ship ship : opponentGrid.getShips()) {
                int[][] shipPosition = ship.getShipPosition();
                for(int i=0;i<ship.getSize();i++) {
                    if(shipPosition[i][0]==row && shipPosition[i][1]==col) {
                        int health=ship.getHealth();
                        health--;
                        ship.setHealth(health);
                        if(ship.isShipSunk()){
                            System.out.println("Excellent!! a Ship of size "+ship.getSize()+" sank!");
                            ship.shipSunkEffect(trackingGrid);
                        }
                        break;
                    }
                }

            }
            opponentGrid.setGrid(opponetBoard);
            trackingGrid.setGrid(trackingBoard);
            if(!opponentGrid.allShipSunk()) {
                System.out.println("Nice! as a bonus, you get another shot!");
            }
            return true;

        }
        else {
            trackingBoard[row][col]='O';
            System.out.println("Miss!");
            opponentGrid.setGrid(opponetBoard);
            trackingGrid.setGrid(trackingBoard);
        }
        return false;
    }
    public boolean isWater(Board trackingGrid) {
        char[][] trackingBoard=trackingGrid.getGrid();
        if(trackingBoard[row][col]=='~')
            return true;
        else
            return false;
    }
}
