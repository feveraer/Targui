package gui;

import domain.DomainController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Frederic
 */
public class PlayersJPanel extends JPanel implements ChangeListener {
    
    private JScrollPane scrPlayers;
    private JTextArea txaPlayers;
    private JButton btnEditPlayer;
    private DomainController dc;
    private ResourceBundle bundle;
    
    private int[] turnsPlayedPerPlayer;
    private int totalTurnsEachPlayer;

    public PlayersJPanel(DomainController dc) {
        this.dc = dc;
        bundle = ResourceBundle.getBundle("properties/game", Locale.ENGLISH);
        initComponents();
        dc.addRepoChangeListener(this);
    }
    
    public void setLanguage(String lang) {
        Locale locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("properties/game", locale);
        btnEditPlayer.setText(bundle.getString("btn.edit"));
        txaPlayers.setText(getAllPlayersText());
    }
    
    public void setTotalTurnsEachPlayer(int turns) {
        totalTurnsEachPlayer = turns;
    }
    
    public void incTurnsPlayed(String player) {
        String[][] players = dc.getPlayers();
        for (int i = 0; i < players.length; i++) {
            if (players[i][0].equals(player)) {
                turnsPlayedPerPlayer[i]++;
                break;
            }
        }
    }
    
    public void resetTurnsPlayedPerPlayer() {
        Arrays.fill(turnsPlayedPerPlayer, 0);
    }
    
    private void initComponents() {
        turnsPlayedPerPlayer = new int[4];
        scrPlayers = new JScrollPane();
        txaPlayers = new JTextArea();
        txaPlayers.setText(getAllPlayersText());
        txaPlayers.setEditable(false);
        txaPlayers.setColumns(40);
        txaPlayers.setRows(5);
        scrPlayers.setViewportView(txaPlayers);
        
        btnEditPlayer = new JButton(bundle.getString("btn.edit"));
        btnEditPlayer.setEnabled(false);
        btnEditPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnEditPlayerActionPerformed();
            }
        });
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnEditPlayer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrPlayers, javax.swing.GroupLayout.DEFAULT_SIZE, 524, 524))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, 85, 85)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnEditPlayer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        setVisible(true);
    }
   
    private String getAllPlayersText() {
        String[][] allPlayers = dc.getPlayers();
        String s = "";
        for (int i = 0; i < dc.getPlayers().length; i++) {
            s += getPlayerInfo(allPlayers[i], turnsPlayedPerPlayer[i]);
        }
        
        return s;
    }
    
    private String getPlayerInfo(String[] p, int turnsPlayed) {
        if (p[0] == null) {
            return "";
        }
        return String.format(bundle.getString("name") + ": %-10s"
                + " " + bundle.getString("color") + ": %-10s"
                + " " + bundle.getString("sector") + ": %-3d"
                + " " + bundle.getString("silver") + ": %-3s"
                + " " + bundle.getString("turn") + ": %2d/%2d\n", 
                p[0], bundle.getString(p[1]), Integer.parseInt(p[2])+1, p[3], turnsPlayed, totalTurnsEachPlayer);
    }
    
    private void btnEditPlayerActionPerformed() {
        new EditPlayerJFrame(dc, bundle);
    }
    
    public void activateBtnEditPlayer() {
        btnEditPlayer.setEnabled(true);
    }
    
    public void updatePane() {
        txaPlayers.setText(getAllPlayersText());
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        updatePane();
    }
}
