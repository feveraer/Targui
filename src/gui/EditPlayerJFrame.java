package gui;

import domain.DomainController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Frederic
 */
public class EditPlayerJFrame extends JFrame implements ChangeListener {

    private JPanel pnlName;
    private JLabel lblOldName;
    private JComboBox cmbOldName;
    private JLabel lblNewName;
    private JTextField txfNewName;
    private JPanel pnlColor;
    private JLabel lblName;
    private JComboBox cmbName;
    private JLabel lblNewColor;
    private JComboBox cmbNewColor;
    private JButton btnCancel;
    private DomainController dc;
    private ResourceBundle bundle;

    public EditPlayerJFrame(DomainController dc, ResourceBundle bundle) {
        dc.addRepoChangeListener(this);
        this.dc = dc;
        this.bundle = bundle;
        initComponents();
    }
    
    private void initComponents() {
        String[] playerNames = getPlayerNames();

        pnlName = new JPanel(new GridLayout(2, 2));
        pnlName.setBorder(BorderFactory.createTitledBorder(bundle.getString("name")));
        lblOldName = new JLabel(bundle.getString("msg.oldName"));
        cmbOldName = new JComboBox(playerNames);
        cmbOldName.setSelectedIndex(-1);
        lblNewName = new JLabel(bundle.getString("msg.newName"));
        txfNewName = new JTextField();
        txfNewName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txfNewNameActionPerformed();
            }
        });
        pnlName.add(lblOldName);
        pnlName.add(cmbOldName);
        pnlName.add(lblNewName);
        pnlName.add(txfNewName);

        pnlColor = new JPanel(new GridLayout(2, 2));
        pnlColor.setBorder(BorderFactory.createTitledBorder(bundle.getString("color")));
        lblName = new JLabel(bundle.getString("msg.name"));
        cmbName = new JComboBox(playerNames);
        cmbName.setSelectedIndex(-1);
        cmbName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmbNameActionPerformed();
            }
        });
        lblNewColor = new JLabel(bundle.getString("msg.newColor"));
        cmbNewColor = new JComboBox(dc.getColors());
        cmbNewColor.setRenderer(new ColorBoxRenderer());
        cmbNewColor.setSelectedIndex(-1);
        cmbNewColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbNewColor.getSelectedIndex() != -1) {
                    cmbNewColorActionPerformed();
                }
            }
        });
        pnlColor.add(lblName);
        pnlColor.add(cmbName);
        pnlColor.add(lblNewColor);
        pnlColor.add(cmbNewColor);

        btnCancel = new JButton(bundle.getString("btn.cancel"));
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCancelActionPerformed();
            }
        });

        setLayout(new BorderLayout());
        add(pnlName, BorderLayout.NORTH);
        add(pnlColor, BorderLayout.CENTER);
        add(btnCancel, BorderLayout.SOUTH);
        setTitle(bundle.getString("btn.edit"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private String[] getPlayerNames() {
        String[][] allPlayers = dc.getPlayers();
        String[] playerNames = new String[allPlayers.length];
        for (int i = 0; i < allPlayers.length; i++) {
            playerNames[i] = allPlayers[i][0];
        }
        return playerNames;
    }

    private List<String> getAvailableColors() {
        String[][] allPlayers = dc.getPlayers();
        List<String> availableColors = new LinkedList<>(Arrays.asList(dc.getColors()));
        for (int i = 0; i < allPlayers.length; i++) {
            availableColors.remove(allPlayers[i][1]);
        }
        return availableColors;
    }

    private void txfNewNameActionPerformed() {
        try {
            dc.setPlayerName((String) cmbOldName.getSelectedItem(), txfNewName.getText());
            txfNewName.setText("");
            cmbOldName.setSelectedIndex(-1);
            cmbName.setSelectedIndex(-1);
        } catch (NullPointerException | IllegalArgumentException exp) {
            JOptionPane.showMessageDialog(null, bundle.getString(exp.getMessage()), bundle.getString("exp.incorrectInput"), JOptionPane.ERROR_MESSAGE);
            txfNewName.setText("");
            txfNewName.requestFocus();
        }
    }

    private void cmbNameActionPerformed() {
        cmbNewColor.setModel(new DefaultComboBoxModel((getAvailableColors().toArray())));
        cmbNewColor.setSelectedIndex(-1);
    }

    private void cmbNewColorActionPerformed() {
        try {
            dc.setPlayerColor((String) cmbName.getSelectedItem(), (String) cmbNewColor.getSelectedItem());
        } catch (NullPointerException | IllegalArgumentException exp) {
            JOptionPane.showMessageDialog(null, bundle.getString(exp.getMessage()), bundle.getString("exp.incorrectInput"), JOptionPane.ERROR_MESSAGE);
            cmbNewColor.setSelectedIndex(-1);
        }
    }

    private void btnCancelActionPerformed() {
        dispose();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        cmbOldName.setModel(new DefaultComboBoxModel(getPlayerNames()));
        cmbName.setModel(new DefaultComboBoxModel(getPlayerNames()));
        cmbOldName.setSelectedIndex(-1);
        cmbName.setSelectedIndex(-1);
        cmbNewColor.setSelectedIndex(-1);
    }
}
