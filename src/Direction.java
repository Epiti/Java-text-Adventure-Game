


public enum Direction {
    NORTH("north", "n"),
    SOUTH("south", "s"),
    EAST("east", "e"),
    WEST("west", "w");

    private final String name;
    private final String shortName;

    Direction(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }



    /** Gets the full name of the direction (e.g., "north"). */
    public String getName() {

        return name;
    }



    /**
     * Parses a string to a Direction, matching either full name or short name.
     * @param userInput the input string (e.g., "north" or "N")
     * @return the corresponding Direction, or null if no match
     */
    public static Direction fromString(String userInput) {
        if (userInput == null) return null;
        String lower = userInput.toLowerCase();
        for (Direction dir : values()) {
            if (dir.name.equals(lower) || dir.shortName.equals(lower)) {
                return dir;
            }
        }
        return null;
    }
}

/*
 * Reference:
 * https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html
 * https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 * https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 */
