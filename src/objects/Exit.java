package objects;

// Exit Class Represents an Exit Object in the Game
public class Exit extends GameObject {

    // Exit Constructor
    public Exit(String emoji, int row, int col) {
        super(emoji, row, col);
    }

    // Check if the Exit is Passable
    public boolean isPassable() {
        return true;
    }

    // Get the Exit's Symbol
    @Override
    public GameObjectType getType() {
        return GameObjectType.EXIT;
    }
}
