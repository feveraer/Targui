package domain;

/**
 * Contains possible colors for each player.
 *
 * @author Frederic Everaert
 * @author Gilles Baert
 * @author Jonas De Bruycker
 * @author Sander De Quick
 */
enum Color {

    BLACK,
    BLUE,
    CYAN,
    GRAY,
    GREEN,
    MAGENTA,
    ORANGE,
    PINK,
    RED,
    WHITE,
    YELLOW;

    /**
     * Checks if the string given exists in this enum.
     *
     * @param str Not case-sensitive.
     * @return Returns true if string matches a value in this enum, false if
     * not.
     */
    public static boolean contains(String str) {
        for (Color k : Color.values()) {
            if (k.name().equals(str.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates and returns an array of strings containing all colors this enum
     * allows.
     *
     * @return
     */
    public static String[] getColors() {
        Color[] c = values();
        String[] str = new String[c.length];
        for (int i = 0; i < c.length; i++) {
            str[i] = c[i].name();
        }
        return str;
    }
}
