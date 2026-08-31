package entities;

// Enemy represents a moving enemy in the game.
public class Enemy extends Entity {

    // Enemy Damage Value
    private final int damage;

    // Enemy Constructor
    public Enemy(String emoji, int damage, int row, int col) {
        super(emoji, row, col);
        this.damage = damage;
    }

    // Get the Enemy's Damage Value
    public int getDamage() {
        return damage;
    }
}