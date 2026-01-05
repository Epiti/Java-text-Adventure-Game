import java.util.*;

/**
 * The Location class represents a place in the game world.
 * Each location has a name, a description, items, connections to other locations,
 * and may contain an animal.
 */

public class Location {
    private final String name;
    private final String description;
    private final List<Item> items;
    private final Map<Direction, Location> connections;
    private Animal animal;


    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
        this.connections = new HashMap<>();
    }



    /** Sets an animal in this location. */
    public void setAnimal(Animal animal) {

        this.animal = animal;
    }


    /** @return the animal in this location */
    public Animal getAnimal() {

        return animal;
    }

    /** Removes the animal from this location. */
    public void removeAnimal() {

        this.animal = null;
    }



    public String getName() {
        return name;
    }


    /** Adds an item to this location.
     * @param item the item to add
     */
    public void addItem(Item item) {
        if (item != null) {
            items.add(item);
        }
    }


    /**
     * Removes an item from this location by name.
     * @param itemName the name of the item to remove
     * @return the removed item, or null if not found
     */
    public Item removeItem(String itemName) {
        Iterator<Item> itm = items.iterator();
        while (itm.hasNext()) {
            Item item = itm.next();
            if (item.getName().equalsIgnoreCase(itemName)) {
                itm.remove();
                return item;
            }
        }
        return null;
    }

    /**
     * Checks if an item with the given name exists here.
     * @param itemName the item name to check
     * @return true if the item is found
     */
    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }



    /**
     * Connects this location to another in a direction.
     * @param direction the direction of the connection
     * @param neighbor the location reached in that direction
     */
    public void addConnection(Direction direction, Location neighbor) {

        connections.put(direction, neighbor);
    }



    /** Returns the location connected in the given direction.
            * @param direction the direction to move
     * @return the neighboring location, or null if none
     */
    public Location getConnection(Direction direction) {

        return connections.get(direction);
    }



    /**
     * @return a full description of the location, including items and animals
     */

    public String getFullDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(description);
        if (!items.isEmpty()) {
            sb.append("\nYou see here: ");
            for (int i = 0; i < items.size(); i++) {
                sb.append(items.get(i).getName());
                if (i < items.size() - 1) sb.append(", ");
            }
            sb.append(".");
        }


        // Show animal if present
        if (animal != null) {
            sb.append("\nA wild ").append(animal.getName()).append(" is here!");
        }

        return sb.toString();
    }
}

/*
 * Reference:
 * Oracle – Classes: https://docs.oracle.com/javase/tutorial/java/javaOO/classes.html
 * Oracle – Map Interface: https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
 * Oracle – List Interface: https://docs.oracle.com/javase/8/docs/api/java/util/List.html
 * Oracle – HashMap Class: https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
 * Oracle – ArrayList Class: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 */