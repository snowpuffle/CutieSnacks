package game;

import levels.Level;
import levels.Level_1;
import levels.Level_2;
import levels.Level_3;
import levels.Level_4;
import levels.Level_5;

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

    // Start Game
    private void startGame() {

        Level[] levels = {
                new Level_1(consoleUI),
                new Level_2(consoleUI),
                new Level_3(consoleUI),
                new Level_4(consoleUI),
                new Level_5(consoleUI),
        };

        for (int i = 0; i < levels.length; i++) {

            Level level = levels[i];

            // Start the Current Level
            if (!gameController.start(level)) {
                return;
            }

            // Check if This Was the Final Level
            if (i == levels.length - 1) {
                consoleUI.print("\n🎉 YOU WIN 🎉\n");
            } else {
                consoleUI.print("\n⭐ LEVEL " + (i + 1) + " COMPLETE! ⭐\n");
                consoleUI.getUserInput(
                        ">> Press [Enter] to Continue: ");
            }
        }
    }
}