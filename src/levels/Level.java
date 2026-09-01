package levels;

import entities.Player;
import game.ConsoleUI;
import game.GameBoard;
import objects.Food;
import objects.GameObject;
import objects.Health;
import objects.Score;
import objects.GameObjectType;

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

    // Level Objects
    protected final GameBoard gameBoard;
    protected final Score score;
    protected final Health health;
    protected final String levelName;

    // Game Managers
    protected final EnemyManager enemyManager;
    protected final LevelRenderer renderer;
    private final LevelBuilder levelBuilder;

    // Console User Interface
    protected final ConsoleUI consoleUI;

    // Level Constructor
    protected Level(ConsoleUI consoleUI, Player player, String levelName) {
        this.consoleUI = consoleUI;
        this.gameBoard = new GameBoard(WIDTH, HEIGHT);
        this.player = player;
        this.score = new Score();
        this.health = new Health(100);
        this.levelName = levelName;
        this.enemyManager = new EnemyManager(player, gameBoard, health);
        this.levelBuilder = new LevelBuilder(gameBoard, enemyManager);
        this.renderer = new LevelRenderer(consoleUI, player, enemyManager, gameBoard);
    }

    // Create the Level Objects from the Maze
    protected void createLevelObjects(char[][] maze, String foodEmoji, String enemyEmoji, String wallEmoji1, String wallEmoji2,
            String exitEmoji, int foodPoints, int enemyDamage) {

        levelBuilder.build(maze, foodEmoji, enemyEmoji, wallEmoji1, wallEmoji2, exitEmoji, foodPoints, enemyDamage);
    }

    // Draw the Level
    public void drawLevel() {
        renderer.drawLevel();
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

        // Handle Enemy Collision
        if (enemyManager.handlePlayerCollision(newRow, newCol)) {
            return false;
        }

        // Handle Static Object Collision
        if (!handleCollision(newRow, newCol)) {
            return false;
        }

        // Move the Player
        player.move(rowChange, colChange);

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
        return object != null && object.getType() == GameObjectType.EXIT;
    }

    // Get the Level Score
    public Score getScore() {
        return score;
    }

    // Get the Level Health
    public Health getHealth() {
        return health;
    }

    // Get the Level Name
    public String getLevelName() {
        return levelName;
    }

}