package objects;

// Food Class Represents a Food Object in the Game
public class Food extends GameObject {

    // Food Point Value
    private final int points;

    // Food Constructor
    public Food(String emoji, int points, int row, int col) {
        super(emoji, row, col);
        this.points = points;
    }

    // Get the Food's Point Value
    public int getPoints() {
        return points;
    }

    // Get the Food's Symbol
    @Override
    public GameObjectType getType() {
        return GameObjectType.FOOD;
    }
}
