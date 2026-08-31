package pathfinding;

import java.util.*;

import entities.Enemy;
import game.GameBoard;

public class BFSPathFinder {

    private static final int[][] DIRECTIONS = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    private final GameBoard gameBoard;

    public BFSPathFinder(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public List<int[]> findPath(
            int startRow,
            int startCol,
            int targetRow,
            int targetCol,
            List<Enemy> enemies) {

        Queue<int[]> queue = new ArrayDeque<>();

        Set<String> visited = new HashSet<>();

        Map<String, String> previous = new HashMap<>();

        String startKey = key(startRow, startCol);
        String targetKey = key(targetRow, targetCol);

        queue.add(new int[]{startRow, startCol});
        visited.add(startKey);

        while (!queue.isEmpty()) {

            int[] current = queue.remove();

            int row = current[0];
            int col = current[1];

            String currentKey = key(row, col);

            if (row == targetRow && col == targetCol) {
                return buildPath(
                        previous,
                        startKey,
                        targetKey
                );
            }

            for (int[] direction : DIRECTIONS) {

                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (!gameBoard.isValidPosition(newRow, newCol)) {
                    continue;
                }

                if (!canMoveTo(newRow, newCol, enemies)) {
                    continue;
                }

                String newKey = key(newRow, newCol);

                if (visited.contains(newKey)) {
                    continue;
                }

                visited.add(newKey);
                previous.put(newKey, currentKey);

                queue.add(new int[]{
                        newRow,
                        newCol
                });
            }
        }

        return Collections.emptyList();
    }

    private boolean canMoveTo(
            int row,
            int col,
            List<Enemy> enemies) {

        if (gameBoard.getGameObjectAt(row, col) != null) {
            return false;
        }

        for (Enemy enemy : enemies) {

            if (enemy.getRow() == row
                    && enemy.getCol() == col) {

                return false;
            }
        }

        return true;
    }

    private List<int[]> buildPath(
            Map<String, String> previous,
            String startKey,
            String targetKey) {

        List<int[]> path = new ArrayList<>();

        String currentKey = targetKey;

        while (!currentKey.equals(startKey)) {

            String[] position = currentKey.split(",");

            int row = Integer.parseInt(position[0]);
            int col = Integer.parseInt(position[1]);

            path.add(new int[]{row, col});

            currentKey = previous.get(currentKey);

            if (currentKey == null) {
                return Collections.emptyList();
            }
        }

        Collections.reverse(path);

        return path;
    }

    private String key(int row, int col) {
        return row + "," + col;
    }
}