package levels;

import entities.Enemy;
import game.GameBoard;
import objects.Exit;
import objects.Food;
import objects.Wall;

// LevelBuilder Creates the Game Objects from the Level Maze.
public class LevelBuilder {

    // Level Objects
    private final GameBoard gameBoard;
    private final EnemyManager enemyManager;

    // LevelBuilder Constructor
    public LevelBuilder(GameBoard gameBoard, EnemyManager enemyManager) {
        this.gameBoard = gameBoard;
        this.enemyManager = enemyManager;
    }

    // Create the Level Objects from the Maze
    public void build(char[][] maze, String foodEmoji, String enemyEmoji, String wallEmoji1, String wallEmoji2,
            String exitEmoji,
            int foodPoints, int enemyDamage) {

        // Loop Through Each Row
        for (int row = 0; row < maze.length; row++) {

            // Loop Through Each Column
            for (int col = 0; col < maze[row].length; col++) {

                createObject(maze[row][col], row, col, foodEmoji, enemyEmoji, wallEmoji1, wallEmoji2, exitEmoji,
                        foodPoints,
                        enemyDamage);
            }
        }
    }

    // Create the Object Based on the Maze Character
    private void createObject(char type, int row, int col, String foodEmoji, String enemyEmoji, String wallEmoji1,
            String wallEmoji2,
            String exitEmoji, int foodPoints, int enemyDamage) {

        switch (type) {

            // Create a Wall
            case '#':
                gameBoard.setGameObjectAt(
                        new Wall(wallEmoji1, row, col));
                break;

            // Create a Wall
            case '%':
                gameBoard.setGameObjectAt(
                        new Wall(wallEmoji2, row, col));
                break;

            // Create Food
            case 'F':
                gameBoard.setGameObjectAt(
                        new Food(foodEmoji, foodPoints, row, col));
                break;

            // Create an Enemy
            case 'E':
                enemyManager.addEnemy(
                        new Enemy(enemyEmoji, enemyDamage, row, col));
                break;

            // Create the Exit
            case 'X':
                gameBoard.setGameObjectAt(
                        new Exit(exitEmoji, row, col));
                break;

            // Leave the Position Empty
            default:
                break;
        }
    }
}