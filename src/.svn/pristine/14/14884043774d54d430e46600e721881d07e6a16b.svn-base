package domain;

/**
 * Represents one square.
 *
 * @author Frederic Everaert
 * @author Gilles Baert
 * @author Jonas De Bruycker
 * @author Sander De Quick
 */
class Square {

    /**
     * Integer representing the amount of attributes of a single square that the
     * persistence layer must keep track of.
     */
    public static final int NUM_OF_ATTR = 4;

    private final Model model;
    private Region region;
    private Player player;
    private int sector;
    private int camels = 0;

    /**
     * A implementation of Model must be given upon creating an instance of
     * Square.
     *
     * @param model
     */
    public Square(Model model) {
        this.model = model;
    }

    /**
     * Returns the region of the current Square.
     *
     * @return
     */
    public Region getRegion() {
        return region;
    }

    /**
     * Sets the region of the current Square.
     *
     * @param region
     */
    public void setRegion(Region region) {
        if (region == null) {
            this.region = region;
            model.fireStateChanged();
        }
        else if (!region.equals(this.region)) {
            this.region = region;
            model.fireStateChanged();
        }
    }

    /**
     * Gets the player of the current Square.
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player of the current Square.
     *
     * @param player
     */
    public void setPlayer(Player player) {
        if (player == null) {
            this.player = player;
            model.fireStateChanged();
        }
        else if (!player.equals(this.player)) {
            this.player = player;
            model.fireStateChanged();
        }
    }

    /**
     * Gets the sector of the current Square.
     *
     * @return
     */
    public int getSector() {
        return sector;
    }

    /**
     * Sets the sector of the current Square.
     *
     * @param sector
     */
    public void setSector(int sector) {
        if (sector < -1 || sector > Board.NUM_OF_SECTORS - 1) {
            throw new IllegalArgumentException("exp.invalidSector");
        }
        if (sector != this.sector) {
            this.sector = sector;
            model.fireStateChanged();
        }
    }

    /**
     * Gets the amount of camels on the current Square.
     *
     * @return
     */
    public int getCamels() {
        return camels;
    }

    /**
     * Sets the amount of camels on the current Square.
     *
     * @param camels
     */
    public void setCamels(int camels) {
        if (camels < 0 || camels > Integer.MAX_VALUE) {
            this.camels = 0;
            model.fireStateChanged();
        } else if (camels != this.camels) {
            this.camels = camels;
            model.fireStateChanged();
        }
    }

}
