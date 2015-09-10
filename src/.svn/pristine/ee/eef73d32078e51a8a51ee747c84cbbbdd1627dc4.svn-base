
import domain.DomainController;
import gui.StartJFrame;
import javax.swing.SwingUtilities;

/**
 * Targui is the singular of Touareg, a warlike people from the central parts of
 * the Sahara Desert.
 *
 * Before the Touareg were pacified by the French early this century, their
 * weapons consisted of a large shield, a sword, spears or a lance, a dagger and
 * a rifle.
 *
 * One of the most characteristic features of the Touareg is the litham or
 * tagalmust, worn only by the men.
 *
 * This is a strip of cotton several metres long. which is wound round the head
 * and covers the face. Thousands of Touareg with their heavily laden camels
 * still trek through the most inhospitable territory. In the past, however, the
 * Touareg, with their fast, white riding camels, the Meharis. derived their
 * power and riches chiefly from brigandage, demanding protection money tram the
 * caravans which passed through their territory. The game is based on this
 * former way of fife of the Touareg.
 *
 * Targui can be played locally with 4 players total.
 *
 * Written in java 7 using: - NetBeans IDE (http://www.netbeans.org) - Visual
 * Paradigm Community (http://www.visual-paradigm.com/)
 *
 * @author Frederic Everaert
 * @author Gilles Baert
 * @author Jonas De Bruycker
 * @author Sander De Quick
 */
public class StartUp {

    /**
     * The main method to start up the Targui GUI.
     *
     * @param args
     */
    public static void main(String[] args) {
        final DomainController dc = new DomainController();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartJFrame(dc);
            }
        });
    }
}
