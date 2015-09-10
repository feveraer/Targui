package domain;

/**
 * Tagging interface implemented by Player and Fortune.
 *
 * @author Frederic Everaert
 * @author Gilles Baert
 * @author Jonas De Bruycker
 * @author Sander De Quick
 */
interface Turn {

    /**
     * Implemented by Player and Fortune to return their details.
     * 
     * @return
     */
    String getDetails();
}
