package levels;

import java.util.List;

import entities.Enemy;
import entities.Player;
import game.GameBoard;
import objects.Health;
import pathfinding.BFSPathFinder;

// EnemyManager Owns Enemy Movement and Behavior.
public class EnemyManager {

    // Objects Needed for Enemy Behavior
    private final Player player;
    private final Health health;

    // Level Enemies
    private final List<Enemy> enemies;

    // BFS Path Finder
    private final BFSPathFinder pathFinder;

    // EnemyManager Constructor
    public EnemyManager(Player player, GameBoard gameBoard, Health health, List<Enemy> enemies) {
        this.player = player;
        this.health = health;
        this.enemies = enemies;
        this.pathFinder = new BFSPathFinder(gameBoard);
    }

    // Add an Enemy
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    // Move All Enemies
    public void moveEnemies() {

        for (Enemy enemy : enemies) {
            moveEnemy(enemy);
        }
    }

    // Move One Enemy Toward the Player
    private void moveEnemy(Enemy enemy) {

        // Attack the Player if Adjacent
        if (isAdjacentToPlayer(enemy)) {
            hitPlayer(enemy);
            return;
        }

        // Find the Shortest Path to the Player
        List<int[]> path = pathFinder.findPath(
                enemy.getRow(),
                enemy.getCol(),
                player.getRow(),
                player.getCol(),
                enemies);

        // No Path Found
        if (path.isEmpty()) {
            return;
        }

        // Get the Next Position
        int[] nextPosition = path.get(0);

        int newRow = nextPosition[0];
        int newCol = nextPosition[1];

        // Move the Enemy One Space
        enemy.move(
                newRow - enemy.getRow(),
                newCol - enemy.getCol());
    }

    // Damage the Player
    private void hitPlayer(Enemy enemy) {
        health.takeDamage(enemy.getDamage());
    }

    // Check if an Enemy is Next to the Player
    private boolean isAdjacentToPlayer(Enemy enemy) {

        int rowDifference = Math.abs(enemy.getRow() - player.getRow());

        int colDifference = Math.abs(enemy.getCol() - player.getCol());

        return rowDifference + colDifference == 1;
    }

    // Find an Enemy at a Position
    public Enemy getEnemyAt(int row, int col) {

        for (Enemy enemy : enemies) {

            if (enemy.getRow() == row
                    && enemy.getCol() == col) {

                return enemy;
            }
        }

        return null;
    }

    // Get All Enemies
    public List<Enemy> getEnemies() {
        return enemies;
    }
}