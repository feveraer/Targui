package domain;

/**
 * Represents one player.
 *
 * @author Frederic Everaert
 * @author Gilles Baert
 * @author Jonas De Bruycker
 * @author Sander De Quick
 */
class Player implements Turn {

    /**
     * The number of attributes a player has.
     */
    public static final int NUM_OF_ATTR = 4;
    private final Model model;
    private String name;
    private Color color;
    private int sector;
    private int silver;

    /**
     * An implementation of model must be given upon creating an instance of
     * Player.
     *
     * @param name
     * @param color
     * @param sector
     * @param model
     */
    public Player(String name, Color color, int sector, Model model) {
        this.model = model;
        setName(name);
        setColor(color);
        setSector(sector);
    }

    /**
     * Returns the name of the player.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name Case-sensitive. May not be emtpy or null.
     * @throws IllegalArgumentException
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("exp.nameNotEmpty");
        }
        if (!name.equals(this.name)) {
            this.name = name;
            model.fireStateChanged();
        }
    }

    /**
     * Returns the color of the player.
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the player.
     *
     * @param color
     */
    public void setColor(Color color) {
        if (!color.equals(this.color)) {
            this.color = color;
            model.fireStateChanged();
        }
    }

    /**
     * Returns the sector of the player.
     *
     * @return
     */
    public int getSector() {
        return sector;
    }

    /**
     * Sets the sector of the player.
     *
     * @param sector Must be a positive integer between 0 and the amount of
     * sectors - 1.
     * @throws IllegalArgumentException
     */
    public void setSector(int sector) throws IllegalArgumentException {
        if (sector < 0 || sector > Board.NUM_OF_SECTORS - 1) {
            throw new IllegalArgumentException(String.format("exp.integerBetween %d", Board.NUM_OF_SECTORS));
        }
        if (sector != this.sector) {
            this.sector = sector;
            model.fireStateChanged();
        }
    }

    /**
     * Returns the amount of silver of the player.
     * 
     * @return 
     */
    public int getSilver() {
        return silver;
    }

    /**
     * Sets the amount of silver of the player.
     * 
     * @param silver Requires a positive integer.
     * @throws IllegalArgumentException 
     */
    public void setSilver(int silver) throws IllegalArgumentException {
        if (silver < 0) {
            throw new IllegalArgumentException("exp.positiveInteger");
        }
        if (silver != this.silver) {
            this.silver = silver;
            model.fireStateChanged();
        }
    }

    /**
     * Returns the details of the player, i.e. his name.
     * 
     * @return 
     */
    @Override
    public String getDetails() {
        return getName();
    }
}
