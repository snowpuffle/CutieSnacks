package game;

import levels.Level;

// GameController Owns the Gameplay Loop and User Input.
public class GameController {

    // Console User Interface
    private final ConsoleUI consoleUI;

    // Game Controller Constructor
    public GameController(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }

    // Start the Game
    public boolean start(Level level) {

        // Run the Game Loop for the Level
        boolean playing = true;

        while (playing) {

            // Display the Current Game Status
            displayGameStatus(level);

            // Display the Current Game Board
            level.drawLevel();

            // Get the Player's Input
            String input = getPlayerInput();

            // Handle the Player Quitting the Game
            if (handleQuit(input)) {
                return false;
            }

            // Play One Game Turn
            playTurn(level, input);

            // Check the Current Game State
            if (!checkGameState(level)) {
                playing = false;
            }

            // Check if the Level is Complete
            if (level.isLevelComplete()) {
                return true;
            }
        }

        return false;
    }

    // Play One Game Turn
    private void playTurn(Level level, String input) {

        // Move the Player
        boolean moved = handleMovement(level, input);

        // Stop the Turn if the Player Did Not Move
        if (!moved) {
            return;
        }

        // Check if the Player Reached the Exit
        if (level.isLevelComplete()) {
            return;
        }

        // Move the Enemies
        level.moveEnemies();
    }

    // Handle the Player's Movement
    private boolean handleMovement(Level level, String input) {

        switch (input) {

            // Move the Player Up
            case "w":
                return level.movePlayer(Level.UP, Level.NO_MOVEMENT);

            // Move the Player Left
            case "a":
                return level.movePlayer(Level.NO_MOVEMENT, Level.LEFT);

            // Move the Player Down
            case "s":
                return level.movePlayer(Level.DOWN, Level.NO_MOVEMENT);

            // Move the Player Right
            case "d":
                return level.movePlayer(Level.NO_MOVEMENT, Level.RIGHT);

            // Handle Invalid Movement Input
            default:
                consoleUI.print("Invalid Move!\n");
                return false;
        }
    }

    // Check the Current Game State
    private boolean checkGameState(Level level) {

        // Check if the Player Died
        if (!level.getHealth().isAlive()) {
            consoleUI.print("\n💀 GAME OVER 💀\n");
            return false;
        }

        return true;
    }

    // Display the Current Game Status
    private void displayGameStatus(Level level) {

        // Get the Level Border Pattern
        String[] border = level.getLevelBorder();

        // Get the Border Symbol
        String symbol = border[0];

        consoleUI.print("\n");
        consoleUI.printBorder(border);
        consoleUI.printEmptyLine(symbol);
        consoleUI.printContent("📍 LEVEL : " + level.getLevelName(), symbol);
        consoleUI.printContent("🏆 SCORE : " + level.getScore().getPoints() + " PTS", symbol);
        consoleUI.printContent(
                "❤️ HEALTH: " + level.getHealth().getCurrentHealth() + "/" + level.getHealth().getMaxHealth(), symbol);
        consoleUI.printEmptyLine(symbol);
        consoleUI.printBorder(border);
    }

    // Get the Player's Input
    private String getPlayerInput() {
        return consoleUI.getUserInput(">> Press [WASD] or [Q]: ").trim().toLowerCase();
    }

    // Check if the Player Wants to Quit
    private boolean handleQuit(String input) {
        return input.equals("q");
    }

}