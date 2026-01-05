import java.util.Scanner;


 /** Story mode for Lost in woods game.
 * The player finds himself/herself in the woods and must reach the town to win.
 */



/** The Game class sets up the world, the player, and manages the main gameplay flow. */
public class Game {
    private final Player player;
    private boolean gameOver;
    private Location startLocation;
    private Location town;
    private Location previousLocation;
    private static final String WELCOME =
            """
                    Welcome to *Lost in the Woods*!\s
                    You wake up lost in the woods surrounded by trees.
                    Find your way to the Town to survive.
                    Be careful — wild animals lurk nearby.
                    """;



    /** Creates the game, initializes the world, and places the player at the start. */
    public Game() {
        player = new Player("Hero", 100, 10);
        gameOver = false;
        initializeWorld();
        player.setLocation(startLocation);
    }



    /** Build the world, items, and animals. */
    private void initializeWorld() {
        Location center = new Location("Forest Center", "You are surrounded by trees.");
        Location darkPath = new Location("Dark Path", "The forest thickens. You sense danger to the south.");
        Location lionsDen = new Location("Lion's Den", "You see a large shadow — lions are near!");
        Location oldTrail = new Location("Old Trail", "A dusty path leads west toward a town.");
        this.town = new Location("Town", "You reached the safety of the town.");

        // Set starting location
        startLocation = center;


        //Connecting all locations.
        center.addConnection(Direction.SOUTH, darkPath);
        center.addConnection(Direction.WEST, oldTrail);

        darkPath.addConnection(Direction.NORTH, center);
        darkPath.addConnection(Direction.SOUTH, lionsDen);

        lionsDen.addConnection(Direction.NORTH, darkPath);

        oldTrail.addConnection(Direction.EAST, center);
        oldTrail.addConnection(Direction.WEST, this.town);

        // add items in location
        darkPath.addItem(new Item("Knife",10));
        lionsDen.addItem(new Item("Sword", 30));
        oldTrail.addItem(new Item("Machete", 40));

        //add animal in location
        Animal lion = new Animal("Lion", 90, 70);
        lionsDen.setAnimal(lion);
        Animal buffalo = new Animal("Buffalo", 60, 40);
        oldTrail.setAnimal(buffalo);
    }



    /** Starts the story mode loop. */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*********************************************************");
        System.out.println(WELCOME);
        printHelp();
        System.out.println("*********************************************************");
        System.out.println("*********************************************************");
        System.out.println(player.getLocation().getFullDescription());
        showInventory();

        while (!gameOver) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                processCommand(input);
            }
        }
        scanner.close();
        System.out.println("Thank you for playing. Goodbye!");
    }





    /** Process player commands. */
    private void processCommand(String input) {
        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String argument = (parts.length > 1 ? parts[1].trim() : "");

        switch (command) {
            case "go":
            case "move":
                handleMove(argument);
                break;

            case "north": case "south": case "east": case "west":
                handleMove(command);
                break;

            case "look":
                doLook();
                break;

            case "take":
                handleTake(argument);
                break;

            case "fight":
                handleFight(argument);
                break;

            case "use":
                handleUse(argument);
                break;

            case "back":
                goBack();
                break;

            case "inventory":
            case "inv":
            case "i":
                showInventory();
                break;

            case "help":
            case "h":
                printHelp();
                break;

            case "quit":
            case "q":
                gameOver = true;
                System.out.println("You quit the game...");
                break;

            default:
                System.out.println("I don't understand that command. Type 'help' for assistance.");
        }

        checkGameStatus();
    }




    /** Moves the player to another location based on the given direction.
     * @param directionInput the direction to move (north, south, east, west)
     */
    private void handleMove(String directionInput) {
        if (directionInput == null || directionInput.isEmpty()) {
            System.out.println("Go where? (south, west, ...)");
            return;
        }

        Direction dir = Direction.fromString(directionInput);
        if (dir == null) {
            System.out.println("Invalid direction. Try other.");
            return;
        }

        Location current = player.getLocation();
        Location next = current.getConnection(dir);

        if (next == null) {
            System.out.println("You can’t go that way.");
            return;
        }

        previousLocation = current;
        player.setLocation(next);
        System.out.println("You move " + dir.getName() + " to " + next.getName() + ".");
        System.out.println(next.getFullDescription());


        //Dangerous encounter check.

        if (next.getAnimal() != null && next.getAnimal().getHealth() > 0) {
            System.out.println("\nYou encountered a " + next.getAnimal().getName() + "!");
            System.out.println("It looks hostile...");
        }
    }




    /** Handles taking an item.
     @param itemName the name of the item to pick up
     */
    private void handleTake(String itemName) {
        if (itemName.isEmpty()) {
            System.out.println("Take what?");
            return;
        }
        Location loc = player.getLocation();
        if (!loc.hasItem(itemName)) {
            System.out.println("There is no " + itemName + " here.");
            return;
        }

        Item item = loc.removeItem(itemName);
        if (item != null) {
            player.addItem(item);
            System.out.println("You pick up the " + item.getName() + ".");
        }
    }



    /** Returns to the previous location if available. */
    private void goBack() {
        if (previousLocation == null) {
            System.out.println("You can’t go back any further.");
            return;
        }
        Location temp = player.getLocation();
        player.setLocation(previousLocation);
        previousLocation = temp;
        System.out.println("You head back to " + player.getLocation().getName() + ".");
        System.out.println(player.getLocation().getFullDescription());
    }



    /** Looks around the current location. */
    private void doLook() {

        System.out.println(player.getLocation().getFullDescription());
    }




    /** Shows inventory. */
    private void showInventory() {
        System.out.println("You are carrying: " + player.getInventoryString());
        System.out.println("Your HP: " + player.getHealth());
    }






    /** Uses an item from the player's inventory to attack an animal.
     * @param itemName the name of the item to use
     */
    private void handleUse(String itemName) {
        if (itemName.isEmpty()) {
            System.out.println("Use what?");
            return;
        }

        Item item = player.getItemByName(itemName);
        if (item == null) {
            System.out.println("You don't have a " + itemName);
            return;
        }

        Animal animal = player.getLocation().getAnimal();
        if (animal == null || !animal.isAlive()) {
            System.out.println("There is nothing to attack here.");
            return;
        }

        // Deal damage based on itemPower
        int damage = item.getitemPower();
        animal.takeDamage(damage);
        System.out.println("You use the " + item.getName() + " and give " + damage + " damage to " + animal.getName() + "!");

        if (!animal.isAlive()) {
            System.out.println("You killed the " + animal.getName() + "!");
            player.getLocation().removeAnimal();
            return;
        }

        // Animal attacks back
        int animalDamage = animal.attack();
        player.takeDamage(animalDamage);
        System.out.println("The " + animal.getName() + " hits you for " + animalDamage + " damage!");
        System.out.println("Your HP: " + player.getHealth());
        System.out.println("The " + animal.getName()+ "'s health: " + animal.getHealth());

        if (!player.isAlive()) {
            System.out.println("You were killed by the " + animal.getName());
            gameOver = true;
        }
    }






    /** Fights an animal using the player's base attack power.
     * @param animalName unused, but kept for command structure
     */
    private void handleFight(String animalName) {
        Animal animal = player.getLocation().getAnimal();
        if (animal == null) {
            System.out.println("There is nothing here to fight.");
            return;
        }

        System.out.println("You fight the " + animal.getName() + " with your bare hands!");

        while (player.isAlive() && animal.isAlive()) {
            int playerDamage = player.getAttack();
            animal.takeDamage(playerDamage);
            System.out.println("You hit the " + animal.getName() + " for " + playerDamage + " damage!");

            if (!animal.isAlive()) {
                System.out.println("You killed the " + animal.getName() + "!");
                player.getLocation().removeAnimal();
                break;
            }

            // Animal attacks back
            int animalDamage = animal.attack();
            player.takeDamage(animalDamage);
            System.out.println("The " + animal.getName() + " hits you for " + animalDamage + " damage!");
            System.out.println("Your Health: " + player.getHealth());
            System.out.println("The " + animal.getName()+ "'s health: " + animal.getHealth());

            if (!player.isAlive()) {
                System.out.println("You were killed by the " + animal.getName() + "...");
                gameOver = true;
                break;
            }
        }
    }






    //Checks if player reached the town or died.
    private void checkGameStatus() {
        if (player.getLocation() == town) {
            System.out.println("Congratulations!!! You survived the forest, You win !!!");
            gameOver = true;
        } else if (!player.isAlive()) {
            System.out.println("You died in the woods...");
            gameOver = true;
        }
    }




    //Displays available commands.
    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("  go <direction> (south, west) - move to another area");
        System.out.println("  back - return to previous location");
        System.out.println("  look - examine your surroundings");
        System.out.println("  take - <item> - pick up an item");
        System.out.println("  use - <item> - use item to battle an enemy");
        System.out.println("  fight - <animal> - fight an enemy with your bare hands");
        System.out.println("  inventory (i) - check your items and HP");
        System.out.println("  help - show this help message");
        System.out.println("  quit - end the game");
    }



    //Main method to start the game.
    public static void main(String[] args) {

        new Game().start();
    }
}
