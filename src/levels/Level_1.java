package levels;

import entities.Player;
import game.ConsoleUI;

// Level 1 Contains the Maze Layout and Objects for Level 1 of the Game.
public class Level_1 extends Level {

    // Level 1 Emojis
    private static final String PLAYER_EMOJI = "🐭";
    private static final String FOOD_EMOJI = "🧀";
    private static final String ENEMY_EMOJI = "🐱";
    private static final String WALL_EMOJI = "🧱";
    private static final String EXIT_EMOJI = "🚪";
    private static final String[] LEVEL_BORDER = { "🧀", "🐭", "🐱", "🍴" };

    // Level Points
    private static final int FOOD_POINTS = 10;
    private static final int ENEMY_DAMAGE = 20;

    // Level 1 Maze Layout
    private static final char[][] MAZE = {
            { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
            { '#', ' ', ' ', ' ', 'F', ' ', '#', ' ', ' ', 'F', ' ', ' ', ' ', ' ', '#' },
            { '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#' },
            { '#', 'F', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#' },
            { '#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#' },
            { '#', ' ', ' ', ' ', 'F', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', 'F', '#' },
            { '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#' },
            { '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', 'E', ' ', '#' },
            { '#', ' ', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', '#' },
            { '#', ' ', ' ', ' ', 'F', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' ', '#' },
            { '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#' },
            { '#', 'F', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' ', '#' },
            { '#', ' ', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', '#', '#', ' ', '#' },
            { '#', ' ', ' ', ' ', 'F', ' ', ' ', ' ', 'F', ' ', ' ', ' ', ' ', ' ', 'X' },
            { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' }
    };

    // Level 1 Constructor
    public Level_1(ConsoleUI consoleUI) {

        // Create the Level 1 Player and Pass it to the Parent Level
        super(consoleUI, new Player(PLAYER_EMOJI, 1, 1));

        // Create the Level 1 Objects and Place Them on the Game Board
        createLevelObjects(MAZE, FOOD_EMOJI, ENEMY_EMOJI, WALL_EMOJI, EXIT_EMOJI, FOOD_POINTS, ENEMY_DAMAGE);
    }

    // Get The Level 1 Border Pattern
    @Override
    public String[] getLevelBorder() {
        return LEVEL_BORDER;
    }
}