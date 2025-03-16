import java.util.Scanner;
public class Game {
    private int GRID_SIZE;
    public void start() {
        boolean playAgain;
        do {
            playGame();
            playAgain = askReplay();
        } while (playAgain);
    }

    private boolean askReplay() {
        System.out.println("Play again? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        return scanner.next().equalsIgnoreCase("yes");
    }

    private void playGame() {
        System.out.println("***** Welcome to Battle Ship Game! *****");
        mainMenu();
    }
    private void singleMode(){

    }
    private void versusMode(){
        Scanner scanner = new Scanner(System.in);
        this.GRID_SIZE=gridSizeMenu();
        System.out.println("Player1, please enter your name:");
        String player1Name=scanner.nextLine();
        System.out.println("Player2, please enter your name:");
        String player2Name=scanner.nextLine();
        Player player1 = new Player(player1Name, GRID_SIZE);
        Player player2 = new Player(player2Name, GRID_SIZE);
        player1.playerGrid.initializeGrid();
        player2.playerGrid.initializeGrid();
        player1.playerTrackingGrid.initializeGrid();
        player2.playerTrackingGrid.initializeGrid();

        System.out.println(player1.getPlayerName()+", do you want to place ships randomly? (yes/no)");
        String option = scanner.nextLine();
        while (!option.equalsIgnoreCase("yes") && !option.equalsIgnoreCase("no")) {
            System.out.println("Invalid option. Try again.");
            option = scanner.nextLine();
        }
        if (option.equalsIgnoreCase("yes")) {
            placeShipRandomly(player1);
        }
        else {
            placeShipManually(player1);
        }
        System.out.println(player2.getPlayerName()+", do you want to place ships randomly? (yes/no)");
        option = scanner.nextLine();
        while (!option.equalsIgnoreCase("yes") && !option.equalsIgnoreCase("no")) {
            System.out.println("Invalid option. Try again.");
            option = scanner.nextLine();
        }
        if (option.equalsIgnoreCase("yes")) {
            placeShipRandomly(player2);
        }
        else {
            placeShipManually(player2);
        }

        boolean player1Turn = true;
//        player1.playerGrid.printBoard();
//        player2.playerGrid.printBoard();
         do{
            if (player1Turn) {
                System.out.println(player1.getPlayerName()+"'s turn:");
                player1.playerTrackingGrid.printBoard();
                player1.playerTurn(player2.playerGrid);
            } else {
                System.out.println(player2.getPlayerName()+"'s turn:");
                player2.playerTrackingGrid.printBoard();
                player2.playerTurn(player1.playerGrid);
            }
            player1Turn = !player1Turn;
        }while (!isGameOver(player1.playerGrid, player2.playerGrid));

        System.out.println("Game Over!");



    }
    private void mainMenu(){
        System.out.println("Select an option :");
        System.out.println("1. 1 VS (AI)  [Single Mode]");
        System.out.println("2. 1 VS 1 [Versus Mode]");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option == 1) singleMode();
        else {
            if (option == 2) versusMode();
            else {
                System.out.println("Invalid option. Try again.");
                mainMenu();
            }
        }
    }
    private int gridSizeMenu(){
        System.out.println("What should be the dimensions of the game's grid? (note that grid size must be between 4 and 27): ");
        Scanner scanner = new Scanner(System.in);
        int GRID_SIZE = scanner.nextInt();
        scanner.nextLine();
        if (GRID_SIZE <= 5 || GRID_SIZE >= 27) {
            System.out.println("Invalid dimensions. Try again.");
            gridSizeMenu();
        }
        return GRID_SIZE;
    }
    private void placeShipRandomly(Player player){
        player.playerGrid.generateShips();
        for (Ship ship : player.playerGrid.getShips()) {
            ShipPlacer.placeShipRandomly(player.playerGrid,ship);
        }
    }
    private void placeShipManually(Player player){
        player.playerGrid.generateShips();
        for (Ship ship : player.playerGrid.getShips()) {
            ShipPlacer.placeShipRandomly(player.playerGrid,ship);
        }
    }
    private boolean isGameOver(Board player1Board, Board player2Board2) {
        if(player1Board.allShipSunk() || player2Board2.allShipSunk())
            return true;
        else return false;
    }


}
