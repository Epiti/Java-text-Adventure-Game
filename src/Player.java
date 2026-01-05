import java.util.List;
import java.util.ArrayList;

/**
 * The Player class represents the main character controlled by the user.
 * A player has a location, an inventory, and inherits health and attack power
 * from the Creature class.
 */

public class Player extends Creature {
    private Location currentLocation;
    private final List<Item> inventory;


    public Player(String name, int health, int attackPower) {
        super(name, health,attackPower);
        this.inventory = new ArrayList<>();

    }


    /** @return the player's base attack power */
    public int getAttack() {
        return attackPower;
    }

    /** @return the location where the player is currently standing */
    public Location getLocation() {
        return currentLocation;
    }


    /**
     * Sets the player's current location.
     * @param location the new location to move the player to
     */
    public void setLocation(Location location) {
        this.currentLocation = location;
    }



    /**
     * Adds an item to the player's inventory.
     * @param item the item to add
     */
    public void addItem(Item item) {
        if (item != null) {
            inventory.add(item);
        }
    }


    /** Finds an item in the player's inventory by name.*/
    public Item getItemByName(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }


    /**
     * @return a text list of all items the player carries
     */
    public String getInventoryString() {
        if (inventory.isEmpty()) {
            return "nothing";
        }
        // Join item names with commas
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inventory.size(); i++) {
            sb.append(inventory.get(i).getName());
            if (i < inventory.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }
}

/*
 * References:
 * Oracle – Classes/Objects: https://docs.oracle.com/javase/tutorial/java/javaOO/classes.html
 * Oracle – Constructors: https://docs.oracle.com/javase/tutorial/java/javaOO/constructors.html
 * Oracle – List / ArrayList:
   https://docs.oracle.com/javase/8/docs/api/java/util/List.html
   https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 * W3Schools – Encapsulation: https://www.w3schools.com/java/java_encapsulation.asp
 * Oracle – String (equalsIgnoreCase): https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#equalsIgnoreCase-java.lang.String-
 * Oracle – StringBuilder: https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html
 */