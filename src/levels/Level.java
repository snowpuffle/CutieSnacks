package levels;

import java.util.ArrayList;
import java.util.List;

import entities.Enemy;
import entities.Player;
import game.ConsoleUI;
import game.GameBoard;
import objects.Exit;
import objects.Food;
import objects.GameObject;
import objects.Health;
import objects.Score;
import objects.Wall;

// Level Owns the Player, Board, Score, Health, and Level Mechanics.
public abstract class Level {

    // Level Dimensions
    protected static final int WIDTH = 15;
    protected static final int HEIGHT = 15;

    // Movement Directions
    public static final int UP = -1;
    public static final int DOWN = 1;
    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    public static final int NO_MOVEMENT = 0;

    // Level Border
    public abstract String[] getLevelBorder();

    // Level Entities
    protected final Player player;
    protected final List<Enemy> enemies;

    // Level Objects
    protected final GameBoard gameBoard;
    protected final Score score;
    protected final Health health;

    // Game Managers
    protected final EnemyManager enemyManager;
    protected final LevelRenderer renderer;

    // Console User Interface
    protected final ConsoleUI consoleUI;

    // Level Constructor
    protected Level(ConsoleUI consoleUI, Player player) {

        this.consoleUI = consoleUI;
        this.player = player;
        this.enemies = new ArrayList<>();
        this.gameBoard = new GameBoard(WIDTH, HEIGHT);
        this.score = new Score();
        this.health = new Health(100);
        this.enemyManager = new EnemyManager(player, gameBoard, health, enemies);
        this.renderer = new LevelRenderer(consoleUI, player, enemies, gameBoard);
    }

    // Create the Level Objects and Enemies
    protected void createLevelObjects(
            char[][] maze,
            String foodEmoji,
            String enemyEmoji,
            String wallEmoji,
            String exitEmoji,
            int foodPoints,
            int enemyDamage) {

        // Loop Through Each Row
        for (int row = 0; row < maze.length; row++) {

            // Loop Through Each Column
            for (int col = 0; col < maze[row].length; col++) {

                switch (maze[row][col]) {

                    // Create a Wall
                    case '#':
                        gameBoard.setGameObjectAt(
                                new Wall(
                                        wallEmoji,
                                        row,
                                        col));
                        break;

                    // Create Food
                    case 'F':
                        gameBoard.setGameObjectAt(
                                new Food(
                                        foodEmoji,
                                        foodPoints,
                                        row,
                                        col));
                        break;

                    // Create an Enemy
                    case 'E':
                        enemyManager.addEnemy(
                                new Enemy(
                                        enemyEmoji,
                                        enemyDamage,
                                        row,
                                        col));
                        break;

                    // Create the Exit
                    case 'X':
                        gameBoard.setGameObjectAt(
                                new Exit(
                                        exitEmoji,
                                        row,
                                        col));
                        break;

                    // Empty Position
                    default:
                        break;
                }
            }
        }
    }

    // Draw the Level
    public void drawLevel() {
        renderer.draw();
    }

    // Move the Player
    public boolean movePlayer(int rowChange, int colChange) {

        // Calculate the New Position
        int newRow = player.getRow() + rowChange;
        int newCol = player.getCol() + colChange;

        // Check if the Position is Inside the Board
        if (!gameBoard.isValidPosition(newRow, newCol)) {
            return false;
        }

        // Check if an Enemy is at the New Position
        Enemy enemy = enemyManager.getEnemyAt(
                newRow,
                newCol);

        // Handle Enemy Collision
        if (enemy != null) {
            hitEnemy(enemy);
            return false;
        }

        // Handle Static Object Collision
        if (!handleCollision(newRow, newCol)) {
            return false;
        }

        // Move the Player
        player.move(
                rowChange,
                colChange);

        return true;
    }

    // Handle Collision with a Static Object
    private boolean handleCollision(int row, int col) {

        GameObject object = gameBoard.getGameObjectAt(row, col);

        // Empty Position
        if (object == null) {
            return true;
        }

        switch (object.getType()) {

            // Collect Food
            case FOOD:
                collectFood((Food) object);
                return true;

            // Block Movement Through Walls
            case WALL:
                return false;

            // Allow Movement Onto Exit
            case EXIT:
                return true;

            // Allow Other Objects
            default:
                return true;
        }
    }

    // Collect Food
    private void collectFood(Food food) {

        // Add Food Points
        score.addPoints(
                food.getPoints());

        // Remove Food From Board
        gameBoard.removeGameObjectAt(
                food.getRow(),
                food.getCol());
    }

    // Handle Enemy Collision
    private void hitEnemy(Enemy enemy) {

        // Reduce Player Health
        health.takeDamage(
                enemy.getDamage());
    }

    // Move All Enemies
    public void moveEnemies() {
        enemyManager.moveEnemies();
    }

    // Check if the Level is Complete
    public boolean isLevelComplete() {

        // Get the Object at the Player's Position
        GameObject object = gameBoard.getGameObjectAt(
                player.getRow(),
                player.getCol());

        // Check if the Player Reached the Exit
        return object != null
                && object.getType() == objects.GameObjectType.EXIT;
    }

    // Get the Level Score
    public Score getScore() {
        return score;
    }

    // Get the Level Health
    public Health getHealth() {
        return health;
    }
}