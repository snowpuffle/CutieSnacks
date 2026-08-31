package entities;

// Entity Class Represents a Game Entity with Position and Emoji Representation
public abstract class Entity {

    // Entity Attributes
    private final String emoji;
    private int row;
    private int col;

    // Entity Constructor
    public Entity(String emoji, int row, int col) {
        this.emoji = emoji;
        this.row = row;
        this.col = col;
    }

    // Move the Entity
    public void move(int rowChange, int colChange) {
        this.row += rowChange;
        this.col += colChange;
    }

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