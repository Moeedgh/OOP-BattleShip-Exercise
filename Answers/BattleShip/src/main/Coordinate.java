public class Coordinate {
    private int row;
    private int col;
    public void stringToCoordinate(String target) {
        char colChar = target.charAt(0);
        char rowChar = target.charAt(1);
        this.row=rowChar-'0';
        this.col=colChar-'A';
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public void missOrHit(Board opponentGrid, Board trackingGrid) {
        char[][] opponetBoard=opponentGrid.getGrid();
        char[][] trackingBoard=trackingGrid.getGrid();
        if(opponetBoard[row][col]=='X') {
            trackingBoard[row][col]='X';
            System.out.println("Hit!");
        }
        else {
            trackingBoard[row][col]='O';
            System.out.println("Miss!");
        }
        opponentGrid.setGrid(opponetBoard);
        trackingGrid.setGrid(trackingBoard);
    }
    public boolean isWater(Board trackingGrid) {
        char[][] trackingBoard=trackingGrid.getGrid();
        if(trackingBoard[row][col]=='~')
            return true;
        else
            return false;
    }
}
