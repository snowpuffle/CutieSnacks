package game;

import levels.Level_1;

public class Game {

    private GameController gameController;
    private ConsoleUI consoleUI;

    // Game Constructor
    public Game() {
        this.consoleUI = new ConsoleUI();
        this.gameController = new GameController(consoleUI);
    }

    public void start() {
        runMenu();
        // startLevel1();
    }

    private void runMenu() {

        boolean runningMenu = true;

        while (runningMenu) {

            // Display the Game Menu
            consoleUI.printHeader("CUTIE SNACKS MENU");
            consoleUI.printMenuOptions();
            consoleUI.printBorder();

            // Get the User's Menu Choice
            String choice = consoleUI.getUserInput(">> Choose an Option [1-3]: ");

            // Handle the User's Menu Choice
            switch (choice) {
                case "1":
                    startGame();
                    break;
                case "2":
                    howToPlay();
                    break;
                case "3":
                    consoleUI.print("\nThanks for Playing!\n");
                    runningMenu = false;
                    break;
                default:
                    consoleUI.print("\nInvalid Option!\n");
            }
        }
    }

    // Display the How to Play Screen
    private void howToPlay() {

        consoleUI.printHeader("HOW TO PLAY");
        consoleUI.printEmptyLine();

        consoleUI.printContent("🎮 Use [WASD] to Move!");
        consoleUI.printContent("🧀 Collect Good!");
        consoleUI.printContent("🐱 Avoid the Enemies!");
        consoleUI.printContent("🚪 Find the Exit!");

        consoleUI.printEmptyLine();
        consoleUI.printBorder();

        consoleUI.getUserInput(">> Press [Enter] to Return to the Menu: ");
    }

    // Start Level 1
    private void startGame() {
        startLevel1();
    }

    // Start Level 1
    private void startLevel1() {
        consoleUI.printHeader("CUTIE SNACKS");

        Level_1 level1 = new Level_1(consoleUI);

        gameController.start(level1);
    }
}