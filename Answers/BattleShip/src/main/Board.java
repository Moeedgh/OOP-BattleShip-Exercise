import java.util.ArrayList;
import java.util.Random;

public class Board {
    private static char[][] grid;
    private int size;
    public ArrayList<Ship> ships;

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
        System.out.print(" ");
        for (char i = 'A'; i < size; i++) {
            System.out.print(i + " ");
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
    public boolean placeShip(Ship ship,int row,int col,boolean horizontal) {
        int[][] shipPosition=ship.getShipPosition();
        if(canPlaceShip(ship,row,col,horizontal)) {
            if(horizontal) {
                for(int j=0;j<ship.getSize();j++) {
                    grid[row][col+j]='X';
                }
                for(int i=0;i<ship.getSize();i++) {
                    for(int j=0;j<2;j++) {
                        if(j==0){
                            shipPosition[i][j]=row;
                        }
                        else{
                            shipPosition[i][j]=col+i;
                        }
                    }
                }
                ship.setShipPosition(shipPosition);
                return true;
            }
            else {
                for(int i=0;i<ship.getSize();i++) {
                    grid[row+i][col]='X';
                }
                for(int i=0;i<ship.getSize();i++) {
                    for(int j=0;j<2;j++) {
                        if(j==0){
                            shipPosition[i][j]=row+i;
                        }
                        else{
                            shipPosition[i][j]=col;
                        }
                    }
                }
                ship.setShipPosition(shipPosition);
                return true;
            }
        }
        return false;

    }
    private static boolean canPlaceShip(Ship ship,int row,int col,boolean horizontal) {
        if (horizontal) {
            for(int j=0;j<ship.getSize();j++) {
                if(col+j==10)
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
                    if((row+i>=0 && row+i<10 ) && (col+j>=0 && col+j<10)){
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
                if (row+i==10)
                    return false;
                if(grid[row+i][col]=='X')
                    return false;
            }
            for(int i=-1;i<ship.getSize()+1;i++) {
                for (int j=-1;j<2;j++){
                    if(j==0 && (i>=0 && i<ship.getSize()))
                        continue;
                    if ((row+i>=0 && row+i<10 ) && (col+j>=0 && col+j<10)){
                        if (grid[row+i][col+j] == 'X')
                            return false;
                    }
                }
            }
            return true;
        }
    }

}
