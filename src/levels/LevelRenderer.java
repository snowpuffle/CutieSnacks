package levels;

import java.util.List;

import entities.Enemy;
import entities.Player;
import game.ConsoleUI;
import game.GameBoard;
import objects.GameObject;

// LevelRenderer Handles the Visual Display of the Level.
public class LevelRenderer {

    // Objects Needed to Draw the Level
    private final ConsoleUI consoleUI;
    private final Player player;
    private final List<Enemy> enemies;
    private final GameBoard gameBoard;

    // LevelRenderer Constructor
    public LevelRenderer(ConsoleUI consoleUI, Player player, List<Enemy> enemies, GameBoard gameBoard) {

        this.consoleUI = consoleUI;
        this.player = player;
        this.enemies = enemies;
        this.gameBoard = gameBoard;
    }

    // Draw the Level
    public void draw() {

        // Create the Level Display
        StringBuilder output = new StringBuilder();

        // Start a New Line Before Drawing the Level
        output.append("\n");

        // Loop Through Each Row
        for (int row = 0; row < gameBoard.getHeight(); row++) {

            // Loop Through Each Column
            for (int col = 0; col < gameBoard.getWidth(); col++) {

                // Display the Player
                if (isPlayerAt(row, col)) {
                    output.append(player.getEmoji());
                    continue;
                }

                // Display an Enemy
                Enemy enemy = getEnemyAt(row, col);

                if (enemy != null) {
                    output.append(enemy.getEmoji());
                    continue;
                }

                // Get the Static Game Object
                GameObject object = gameBoard.getGameObjectAt(row, col);

                // Display the Static Game Object
                if (object != null) {
                    output.append(object.getEmoji());
                } else {
                    // Display an Empty Space
                    output.append("  ");
                }
            }

            // Move to the Next Row
            output.append("\n");
        }

        // Display the Completed Level
        consoleUI.print(output.toString());
    }

    // Check if the Player is at a Position
    private boolean isPlayerAt(int row, int col) {

        return player.getRow() == row
                && player.getCol() == col;
    }

    // Find an Enemy at a Position
    private Enemy getEnemyAt(int row, int col) {

        for (Enemy enemy : enemies) {

            if (enemy.getRow() == row
                    && enemy.getCol() == col) {

                return enemy;
            }
        }

        return null;
    }
}