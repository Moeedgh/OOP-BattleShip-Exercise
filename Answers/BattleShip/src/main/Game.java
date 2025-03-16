import java.util.Scanner;
public class Game {
    private int GRID_SIZE;
    private Scanner scanner = new Scanner(System.in);
    private Scanner inputInt = new Scanner(System.in);
    public void start() {
        boolean playAgain;
        do {
            playGame();
            playAgain = askReplay();
        } while (playAgain);
    }

    private boolean askReplay() {
        System.out.println("Play again? (yes/no)");
        return scanner.next().equalsIgnoreCase("yes");
    }

    private void playGame() {
        System.out.println("***** Welcome to Battle Ship Game! *****");
        mainMenu();
    }
    private void singleMode(){
        System.out.println("***** Single mode *****");
    }
    private void versusMode(){
        System.out.println("***** Versus Mode *****");
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
        Board.generateShips(GRID_SIZE);
        choosePlaceShipMenu(player1);
        choosePlaceShipMenu(player2);
        Utils.shipsInfo(player1.playerGrid);
        boolean player1Turn = true;
        player1.playerGrid.printBoard();
        player2.playerGrid.printBoard();
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
        }while (!isGameOver(player1, player2));

        System.out.println("Game Over!");



    }
    private void mainMenu(){
        System.out.println("Select an option :");
        System.out.println("1. 1 VS (AI)  [Single Mode]");
        System.out.println("2. 1 VS 1 [Versus Mode]");
        int option = inputInt.nextInt();
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
        System.out.println("What should be the dimensions of the game's grid? (note that grid size must be between 5 and 27): ");
        int GRID_SIZE = inputInt.nextInt();
        if (GRID_SIZE <= 5 || GRID_SIZE >= 27) {
            System.out.println("Invalid dimensions. Try again.");
            gridSizeMenu();
        }
        return GRID_SIZE;
    }

    private boolean isGameOver(Player player1, Player player2) {
        if(player1.playerGrid.allShipSunk()){
            System.out.println(player1.getPlayerName()+" Won!! 🎉");
            return true;
        }
        if(player2.playerGrid.allShipSunk()){
            System.out.println(player1.getPlayerName()+" Won!! 🎉");
            return true;
        }
        return false;
    }
    private void choosePlaceShipMenu(Player player){
        System.out.println(player.getPlayerName()+", Choose one of the options below to place ships: ");
        System.out.println("1. Place Ship Randomly");
        System.out.println("2. Place Ship Manually");
        int option = inputInt.nextInt();
        if (option == 1) Utils.placeShipRandomly(player);
        else{
            if (option == 2) Utils.placeShipManually(player);
            else {
                System.out.println("Invalid option. Try again.");
                choosePlaceShipMenu(player);
            }
        }
    }

}
