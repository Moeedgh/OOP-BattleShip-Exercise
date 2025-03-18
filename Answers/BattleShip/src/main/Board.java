import java.util.ArrayList;
import java.util.Random;

public class Board {
    private char[][] grid;
    private final int size;
    private ArrayList<Ship> ships= new ArrayList<>();

    public Board(int size) {
        this.size = size;
        this.grid = new char[size][size];
    }

    public int getSize() {
        return size;
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid){
        this.grid = grid;
    }
    public void initializeGrid() {
        for (int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                grid[i][j]='~';
            }
        }
    }
    public void printBoard() {
        System.out.print("  ");
        for (int i = 65; i < 65+size; i++) {
            System.out.print((char) i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public  ArrayList<Ship> getShips() {
        return ships;
    }

    public void generateShips() {
        final int[] SHIP_SIZES = {2,3,4,5,6};
        final double SHIP_AREA_RATIO = 0.25;
        int totalBoardArea = size * size;
        int totalShipArea = (int) (totalBoardArea * SHIP_AREA_RATIO);


        int i=0;
        while (totalShipArea > 1) {
            if(i==4)
                i=0;
            int shipSize = SHIP_SIZES[i];
            if (shipSize > totalShipArea) {
                i++;
                continue;
            }
            Ship ship = new Ship(shipSize);
            ships.add(ship);
            totalShipArea -= shipSize;
            i++;
        }
    }
    public boolean placeShip(Ship ship,int row,int col,boolean horizontal) {
        int[][] shipPosition=ship.getShipPosition();
        if(canPlaceShip(ship,row,col,horizontal)) {
            if(horizontal) {
                ship.setHorizontal(true);
                for(int j=0;j<ship.getSize();j++) {
                    grid[row][col+j]='X';
                }
                for(int i=0;i<ship.getSize();i++) {
                    shipPosition[i][0]=row;
                    shipPosition[i][1]=col+i;
                }
                ship.setShipPosition(shipPosition);
                return true;
            }
            else {
                ship.setHorizontal(false);
                for(int i=0;i<ship.getSize();i++) {
                    grid[row+i][col]='X';
                }
                for(int i=0;i<ship.getSize();i++) {
                        shipPosition[i][0]=row+i;
                        shipPosition[i][1]=col;
                }
                ship.setShipPosition(shipPosition);
                return true;
            }
        }
        return false;

    }
    private boolean canPlaceShip(Ship ship,int row,int col,boolean horizontal) {
        if (horizontal) {
            for(int j=0;j<ship.getSize();j++) {
                if(col+j==size)
                    return false;
                if (grid[row][col+j] == 'X') {
                    return false;
                }
            }
            for(int j=-1;j<ship.getSize()+1;j++) {
                for (int i=-1;i<2;i++){
                    if(i==0 && (j>=0 && j<ship.getSize())) {
                        continue;
                    }
                    if((row+i>=0 && row+i<size ) && (col+j>=0 && col+j<size)){
                        if (grid[row+i][col+j] == 'X') {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        else {
            for(int i=0;i<ship.getSize();i++) {
                if (row+i==size)
                    return false;
                if(grid[row+i][col]=='X')
                    return false;
            }
            for(int i=-1;i<ship.getSize()+1;i++) {
                for (int j=-1;j<2;j++){
                    if(j==0 && (i>=0 && i<ship.getSize()))
                        continue;
                    if ((row+i>=0 && row+i<size ) && (col+j>=0 && col+j<size)){
                        if (grid[row+i][col+j] == 'X')
                            return false;
                    }
                }
            }
            return true;
        }
    }

    public boolean allShipSunk(){//Utils
        for(Ship ship:ships) {
            if(ship.getHealth()!=0){
                return false;
            }
        }
        return true;
    }
}
