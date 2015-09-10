package domain;

/**
 * Respresents the different region types along with their attributes.
 *
 * @author Frederic Everaert
 * @author Gilles Baert
 * @author Jonas De Bruycker
 * @author Sander De Quick
 */
enum Region {

    SALTMINE(5, 5),
    SETTLEMENT(4, 4),
    GUELTA(3, 3),
    ERG(1, 0),
    MOUNTAIN(0, 1),
    RAG(2, 0),
    FESHFESH(0, 2),
    SALTLAKE(0, 0);

    // Economic value.
    private final int ECON_VALUE;

    // Strategic value.
    private final int STRAT_VALUE;

    private int currentEconValue;
    private int currentStratValue;

    /**
     * Every Region has a specific economic value and a specific strategic
     * value. These values can temporarily change for a given round and as such
     * this class holds the default values as well as temporary ones.
     *
     * @param econValue
     * @param stratValue
     */
    Region(int econValue, int stratValue) {
        this.ECON_VALUE = econValue;
        this.STRAT_VALUE = stratValue;
        setCurrentEconValue(econValue);
        setCurrentStratValue(stratValue);
    }

    /**
     * Checks if the string given exists in this enum.
     *
     * @param str Not case-sensitive.
     * @return Returns true if string matches a value in this enum, false if
     * not.
     */
    public static boolean contains(String str) {
        for (Region k : Region.values()) {
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
    public static String[] getRegions() {
        Region[] r = values();
        String[] str = new String[r.length];
        for (int i = 0; i < r.length; i++) {
            str[i] = r[i].name();
        }
        return str;
    }

    /**
     * Returns the current economic value.
     *
     * @return
     */
    public int getCurrentEconValue() {
        return currentEconValue;
    }

    /**
     * Returns the current strategic value.
     *
     * @return
     */
    public int getCurrentStratValue() {
        return currentStratValue;
    }

    /**
     * Sets the current economic value.
     *
     * @param econValue
     */
    public void setCurrentEconValue(int econValue) {
        currentEconValue = econValue;
    }

    /**
     * Sets the current strategic value.
     *
     * @param stratValue
     */
    public void setCurrentStratValue(int stratValue) {
        currentStratValue = stratValue;
    }

    /**
     * Resets both economic and strategic values of all types back to their
     * default values.
     */
    public void resetCurrentValues() {
        currentEconValue = ECON_VALUE;
        currentStratValue = STRAT_VALUE;
    }
}
