import java.util.Scanner;
public class Game {
    private int GRID_SIZE=10;
    private boolean advancedMode=false;
    private Scanner scanner = new Scanner(System.in);
    private Scanner inputInt = new Scanner(System.in);
    private Scanner pause = new Scanner(System.in);
    public void start() {
        mainMenu();
        System.out.println("GAME OVER!");
    }

    private boolean askReplay() {
        System.out.println("Play again? (yes/no)");
        return scanner.next().equalsIgnoreCase("yes");
    }

    private void playGame() {
        gameTypeMenu();
    }
    private void singleMode(){
        String playerName;
        do {
            System.out.println("Please enter your name:");
            playerName = scanner.nextLine();
            if (playerName.isBlank()){
                System.out.println("Name cannot be empty!! 😡");
            }
        }while (playerName.isBlank());
        Player player = new Player(playerName, GRID_SIZE);
        AIPlayer aiPlayer= new AIPlayer("AI",GRID_SIZE);
        player.playerGrid.initializeGrid();
        aiPlayer.playerGrid.initializeGrid();
        player.playerTrackingGrid.initializeGrid();
        aiPlayer.playerTrackingGrid.initializeGrid();
        player.playerGrid.generateShips();
        aiPlayer.playerGrid.generateShips();
        choosePlaceShipMenu(player);
        Utils.placeShipRandomly(aiPlayer);
        Utils.shipsInfo(player.playerGrid);
        boolean playerTurn = true;
        do{
            if (playerTurn) {
                System.out.println(player.getPlayerName()+"'s turn:");
                player.playerTrackingGrid.printBoard();
                player.playerTurn(aiPlayer.playerGrid,advancedMode);
                pause.nextLine();
            } else {
                System.out.println(aiPlayer.getPlayerName()+"'s turn:");
                aiPlayer.playerTrackingGrid.printBoard();
                aiPlayer.aiTurn(player.playerGrid);
                pause.nextLine();
            }
            playerTurn = !playerTurn;
        }while (!isGameOver(player, aiPlayer));

    }
    private void versusMode(){
        System.out.println("***************** Versus Mode *****************");
        String player1Name;
        do {
            System.out.println("Player1, please enter your name:");
            player1Name = scanner.nextLine();
            if(player1Name.isBlank()){
                System.out.println("Name cannot be empty!! 😡");
            }
        }while (player1Name.isBlank());
        String player2Name;
        do{
            System.out.println("Player2, please enter your name:");
            player2Name=scanner.nextLine();
            if(player2Name.isBlank()){
                System.out.println("Name cannot be empty!! 😡");
            }
            if(player1Name.equals(player2Name)){
                System.out.println("You are already playing!😡🤨");
                System.out.println("Your name should not be the same as the first player's name.😡");
            }
            else
                break;
        } while (true);
        Player player1 = new Player(player1Name, GRID_SIZE);
        Player player2 = new Player(player2Name, GRID_SIZE);
        player1.playerGrid.initializeGrid();
        player2.playerGrid.initializeGrid();
        player1.playerTrackingGrid.initializeGrid();
        player2.playerTrackingGrid.initializeGrid();
        player1.playerGrid.generateShips();
        player2.playerGrid.generateShips();
        choosePlaceShipMenu(player1);
        choosePlaceShipMenu(player2);
        Utils.shipsInfo(player1.playerGrid);
        boolean player1Turn = true;
         do{
            if (player1Turn) {
                System.out.println(player1.getPlayerName()+"'s turn:");
                player1.playerTrackingGrid.printBoard();
                player1.playerTurn(player2.playerGrid,advancedMode);
                pause.nextLine();
            } else {
                System.out.println(player2.getPlayerName()+"'s turn:");
                player2.playerTrackingGrid.printBoard();
                player2.playerTurn(player1.playerGrid,advancedMode);
                pause.nextLine();
            }
            player1Turn = !player1Turn;
        }while (!isGameOver(player1, player2));

    }
    private void gameTypeMenu(){
        System.out.println("******** Game Type ********");
        System.out.println("1.Classic");
        System.out.println("2.Advanced");
        System.out.println("3.Back to main menu");
        System.out.println("****************************");
        System.out.print("Enter your choice:");
        do {
            switch (inputInt.nextInt()) {
                case 1:
                    modeMenu();
                    return;
                case 2:
                    advancedMode=true;
                    GRID_SIZE=10;
                    modeMenu();
                    return;
                case 3:
                    mainMenu();
                default:
                    System.out.print("Invalid choice, try again:");
            }
        }while (true);
    }
    private void modeMenu(){
        System.out.println("******** Game Mode ********");
        System.out.println("1. 1 VS (AI)  [Single Mode]");
        System.out.println("2. 1 VS 1 [Versus Mode]");
        System.out.println("3. Back to game type menu");
        System.out.println("****************************");
        System.out.print("Enter your choice:");
        do {
            int choice = inputInt.nextInt();
            switch (choice) {
                case 1:
                    singleMode();
                    return;
                case 2:
                    versusMode();
                    return;
                case 3:
                    gameTypeMenu();
                    return;
                default:
                    System.out.print("Invalid choice, try again:");
            }
        }while (true);
    }
    private void gridSizeSetting(){
        System.out.println("************* Grid Size Setting *************");
        System.out.println("Grid size = 10");
        System.out.println("Dimensions of the game's grid is 10 X 10");
        System.out.println("What should be the dimensions of the game's grid? (note that grid size must be between 5 and 27): ");
        int GRID_SIZE = inputInt.nextInt();
        if (GRID_SIZE <= 5 || GRID_SIZE >= 27) {
            System.out.println("Invalid dimensions. Try again.");
            gridSizeSetting();
        }
        else {
            System.out.println("Grid size updated successfully.");
            System.out.println("Now, Dimensions of the game's grid is : " + GRID_SIZE+" X "+GRID_SIZE);
            this.GRID_SIZE = GRID_SIZE;
            pause.nextLine();
        }
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
        System.out.println("Enter your choice:");
        do {
            int option = inputInt.nextInt();
            switch (option) {
                case 1:
                    Utils.placeShipRandomly(player);
                    return;
                case 2:
                    Utils.placeShipManually(player);
                    return;
                default:
                    System.out.print("Invalid option. Try again:");
            }
        }while (true);
    }
    private void mainMenu(){
        System.out.println("***** Welcome to Battle Ship Game! *****");
        System.out.println("1.Play Game");
        System.out.println("2.Settings");
        System.out.println("3.Exit");
        System.out.println("*****************************************");
        System.out.print("Enter your choice:");
        do {
            int option = inputInt.nextInt();
            switch (option) {
                case 1:
                    boolean playAgain;
                    do {
                        playGame();
                        playAgain = askReplay();
                    } while (playAgain);
                    return;
                case 2:
                    settings();
                    return;
                case 3:
                    return;
                default:
                    System.out.print("Invalid choice. Try again:");
            }
        }while (true);

    }
    private void settings(){
        System.out.println("******** Settings ********");
        System.out.println("1.Grid Size Setting");
        System.out.println("2.Back to Main Menu");
        System.out.println("****************************");
        System.out.print("Enter your choice:");
        do {
            int option = inputInt.nextInt();
            switch (option) {
                case 1:
                    gridSizeSetting();
                    mainMenu();
                    return;
                case 2:
                    mainMenu();
                    return;
                default:
                    System.out.print("Invalid choice. Try again:");
            }
        }while (true);
    }

}
