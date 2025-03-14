

public class Ship {
    private int size;
    private int health;

    int[][] shipPosition;
    public Ship(int size){
        this.size=size;
        health = size;
        shipPosition = new int[size][2];
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
        return false;
    }
}
