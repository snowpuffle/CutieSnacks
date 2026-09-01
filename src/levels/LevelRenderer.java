package levels;

import entities.Enemy;
import entities.Player;
import game.ConsoleUI;
import game.GameBoard;
import objects.GameObject;

// LevelRenderer Handles the Visual Display of the Level
public class LevelRenderer {

    // Objects Needed to Draw the Level
    private final ConsoleUI consoleUI;
    private final Player player;
    private final EnemyManager enemyManager;
    private final GameBoard gameBoard;

    // LevelRenderer Constructor
    public LevelRenderer(ConsoleUI consoleUI, Player player, EnemyManager enemyManager, GameBoard gameBoard) {
        this.consoleUI = consoleUI;
        this.player = player;
        this.enemyManager = enemyManager;
        this.gameBoard = gameBoard;
    }

    // Draw the Level by Displaying the Player, Enemies, Objects, and Empty Spaces.
    public void drawLevel() {

        // Create the Level Display
        StringBuilder output = new StringBuilder();
        output.append("\n");

        // Draw Each Row of the Level
        for (int row = 0; row < gameBoard.getHeight(); row++) {
            // Draw the Row and Append it to the Level Display
            output.append(drawRow(row));
        }

        // Display the Level
        consoleUI.print(output.toString());
    }

    // Draw a Single Row of the Level
    private String drawRow(int row) {

        // Create the Row Display
        StringBuilder output = new StringBuilder();

        // Draw Each Position in the Row
        for (int col = 0; col < gameBoard.getWidth(); col++) {
            output.append(getSymbolAt(row, col));
        }

        output.append("\n");

        return output.toString();
    }

    // Get the Symbol to Display at a Position
    private String getSymbolAt(int row, int col) {

        // Display the Player
        if (isPlayerAt(row, col)) {
            return player.getEmoji();
        }

        // Display an Enemy
        Enemy enemy = enemyManager.getEnemyAt(row, col);

        if (enemy != null) {
            return enemy.getEmoji();
        }

        // Display a Static Game Object
        GameObject object = gameBoard.getGameObjectAt(row, col);

        if (object != null) {
            return object.getEmoji();
        }

        // Display an Empty Space
        return "  ";
    }

    // Check if the Player is at a Position
    private boolean isPlayerAt(int row, int col) {
        return player.getRow() == row && player.getCol() == col;
    }
}