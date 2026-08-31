package objects;

public abstract class GameObject {

    // Game Object Attributes
    private final String emoji;
    private final int row;
    private final int col;

    // Game Object Constructor
    public GameObject(String emoji, int row, int col) {
        this.emoji = emoji;
        this.row = row;
        this.col = col;
    }

    // Get the Game Object Type
    public abstract GameObjectType getType();

    // Getters
    public String getEmoji() {
        return emoji;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
