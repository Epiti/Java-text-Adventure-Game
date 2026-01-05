/**
 * The Creature class represents any living being (Player or Animal) in the game.
 * Both players and animals inherit from this class.
 * A creature has a name, health, and an attack power.
 */


public abstract class Creature {
    protected final String name;
    protected int health;
    protected int attackPower;

    public Creature(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName() {

        return name;
    }

    public int getHealth() {

        return health;
    }

    public boolean isAlive() {

        return health > 0;
    }


    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    public int attack() {

        return attackPower;
    }
}


