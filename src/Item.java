/** The Item class represents a tangible object in the game that the player can collect.
 * Each item has a name used to identify it in commands.
 */

public class Item {
    private final String name;
    private int itemPower;


    /** Constructs an Item with the given name and item power.
     * @param name the name of the item (used for identification in commands)
     * @param itemPower the power attack of item
     */
    public Item(String name, int itemPower) {

        this.name = name;
        this.itemPower = itemPower;
    }

    /** @return the name of this item */
    public String getName() {

        return name;
    }

    /** @return the power attack of this item */
    public int getitemPower() {

        return itemPower;
    }



    /** @return a string representation (the item name) */
    public String toString() {
        return name;
    }
}
/*
 * Reference1: https://docs.oracle.com/javase/tutorial/java/javaOO/classes.html
 * Reference2: https://docs.oracle.com/javase/tutorial/java/javaOO/constructors.html
 * Reference3: https://www.w3schools.com/java/java_encapsulation.asp
 */