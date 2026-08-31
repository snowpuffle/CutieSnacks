package objects;

// Wall Class Represents a Wall Object in the Game
public class Wall extends GameObject {

    // Wall Constructor
    public Wall(String emoji, int row, int col) {
        super(emoji, row, col);
    }

    // Check if the Wall is Passable
    public boolean isPassable() {
        return false;
    }

    // Get the Wall's Symbol
    @Override
    public GameObjectType getType() {
        return GameObjectType.WALL;
    }

}
