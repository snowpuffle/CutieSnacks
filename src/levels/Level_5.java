package levels;

import entities.Player;
import game.ConsoleUI;

// Level 5 Contains the Maze Layout and Objects for Level 5 of the Game.
public class Level_5 extends Level {

    // Level Emojis
    private static final String PLAYER_EMOJI = "🐠";
    private static final String FOOD_EMOJI = "🦐";
    private static final String ENEMY_EMOJI = "🦈";
    private static final String WALL_EMOJI_1 = "🪸";
    private static final String WALL_EMOJI_2 = "🐚";
    private static final String EXIT_EMOJI = "🌀";
    private static final String[] LEVEL_BORDER = { "🦐", "🐠", "🪸", "🌀" };

    // Level Information
    private static final String LEVEL_NAME = "FISHY FISHY";
    private static final int FOOD_POINTS = 30;
    private static final int ENEMY_DAMAGE = 50;

    // Level 5 Maze Layout - 4 Enemies
    private static final char[][] MAZE = {
            { '#', '#', '#', '%', '#', '#', '#', '%', '#', '#', '%', '#', '#', '#', '#' },
            { '#', ' ', ' ', ' ', 'F', ' ', '#', ' ', ' ', ' ', 'F', ' ', ' ', ' ', '#' },
            { '%', ' ', '#', '#', '%', ' ', '#', ' ', '#', '%', '#', ' ', '#', ' ', '#' },
            { '#', 'F', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', '#', ' ', '#' },
            { '#', ' ', '%', ' ', '#', '#', '#', ' ', '#', ' ', '#', '%', '#', ' ', '#' },
            { '#', ' ', ' ', ' ', 'F', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', 'F', '%' },
            { '#', '#', '#', ' ', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#' },
            { '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', 'E', ' ', '%' },
            { '%', ' ', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#' },
            { '#', ' ', ' ', ' ', 'F', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' ', '#' },
            { 'X', ' ', '#', '#', '%', '#', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#' },
            { '#', 'F', ' ', ' ', ' ', ' ', 'E', ' ', ' ', ' ', 'F', ' ', ' ', ' ', '#' },
            { '#', '%', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', '#', '#', ' ', '#' },
            { '#', ' ', ' ', 'E', 'F', ' ', ' ', ' ', 'F', ' ', ' ', ' ', 'E', ' ', '#' },
            { '#', '#', '%', '#', '#', '#', '#', '%', '#', '#', '#', '#', '%', '#', '#' }
    };

    // Level Constructor
    public Level_5(ConsoleUI consoleUI) {

        // Create the Level Player and Pass it to the Parent Level
        super(consoleUI, new Player(PLAYER_EMOJI, 1, 1), LEVEL_NAME);

        // Create the Level Objects and Place Them on the Game Board
        createLevelObjects(MAZE, FOOD_EMOJI, ENEMY_EMOJI, WALL_EMOJI_1, WALL_EMOJI_2, EXIT_EMOJI, FOOD_POINTS,
                ENEMY_DAMAGE);
    }

    // Get the Level Border Pattern
    @Override
    public String[] getLevelBorder() {
        return LEVEL_BORDER;
    }
}