

public class Ship {
    private final int size;
    private int health;
    private boolean horizontal;
    private int[][] shipPosition;


    public Ship(int size){
        this.size=size;
        health = size;
        shipPosition = new int[size][2];
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSize() {
        return size;
    }

    public int getHealth() {
        return health;
    }

    public int[][] getShipPosition() {
        return shipPosition;
    }

    public void setShipPosition(int[][] shipPosition) {
        this.shipPosition = shipPosition;
    }
    public boolean isShipSunk(){
        if (health==0)
            return true;
        return false;
    }
    public void shipSunkEffect(Board trackingGrid){       //Utils
        int boardSize = trackingGrid.getSize();
        char[][] grid=trackingGrid.getGrid();
        int row = shipPosition[0][0];
        int col = shipPosition[0][1];
        if (horizontal) {
            for(int j=-1;j<size+1;j++) {
                for (int i=-1;i<2;i++){
                    if(i==0 && (j>=0 && j<size)) {
                        continue;
                    }
                    if((row+i>=0 && row+i<boardSize ) && (col+j>=0 && col+j<boardSize)){
                        grid[row+i][col+j] = 'O';
                    }
                }
            }
            trackingGrid.setGrid(grid);
        }
        else {
            for(int i=-1;i<size+1;i++) {
                for (int j=-1;j<2;j++){
                    if(j==0 && (i>=0 && i<size))
                        continue;
                    if ((row+i>=0 && row+i<boardSize ) && (col+j>=0 && col+j<boardSize)){
                        grid[row+i][col+j] = 'O';
                    }
                }
            }
            trackingGrid.setGrid(grid);
        }
    }
}
