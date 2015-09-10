package gui;

import domain.DomainController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Frederic
 */
public class GameJPanel extends JPanel implements ChangeListener {

    private BoardJPanel pnlBoard;
    private StartJFrame frmStart;
    private JButton btnInitBoard;
    private JButton btnSetRounds;
    private JButton btnPlay;
    private JLabel lblTxtNumberOfRounds;
    private JLabel lblNumberOfRounds;
    private JLabel lblTxtCurrentRound;
    private JLabel lblCurrentRound;
    private JLabel lblTxtNumberOfTurns;
    private JLabel lblNumberOfTurns;
    private JLabel lblTxtCurrentTurn;
    private JLabel lblCurrentTurn;
    private JScrollPane scrBoardInfo;
    private JTextArea txaBoardInfo;
    private boolean[] finalizedRounds;
    private DomainController dc;
    private ResourceBundle bundle;

    public GameJPanel(StartJFrame frmStart, DomainController dc) {
        this.frmStart = frmStart;
        this.dc = dc;
        bundle = ResourceBundle.getBundle("properties/game", Locale.ENGLISH);
        initComponents();
    }

    public void setLanguage(String lang) {
        Locale locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("properties/game", locale);
        update();
        pnlBoard.setLanguage(lang);
    }

    private void update() {
        btnInitBoard.setText(bundle.getString("btn.initBoard"));
        btnSetRounds.setText(bundle.getString("btn.setRounds"));
        //!!!
        btnPlay.setText(bundle.getString("btn.nextRound"));
        lblTxtNumberOfRounds.setText(bundle.getString("lbl.numberOfRounds") + ":");
        lblTxtCurrentRound.setText(bundle.getString("lbl.currentRound") + ":");
        lblTxtNumberOfTurns.setText(bundle.getString("lbl.numberOfTurns") + ":");
        lblTxtCurrentTurn.setText(bundle.getString("lbl.currentTurn") + ":");
    }
    
    private void initComponents() {
        pnlBoard = new BoardJPanel(dc, this);
        scrBoardInfo = new JScrollPane();
        txaBoardInfo = new JTextArea();
        txaBoardInfo.setText("");
        txaBoardInfo.setEditable(false);
        scrBoardInfo.setViewportView(txaBoardInfo);
        txaBoardInfo.setColumns(20);
        btnInitBoard = new JButton(bundle.getString("btn.initBoard"));
        btnInitBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnInitBoardActionPerformed();
            }
        });
        btnInitBoard.setEnabled(false);
        btnSetRounds = new JButton(bundle.getString("btn.setRounds"));
        btnSetRounds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSetRoundsActionPerformed();
            }
        });
        btnSetRounds.setEnabled(false);
        btnPlay = new JButton(bundle.getString("btn.nextRound"));
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPlayActionPerformed();
            }
        });
        btnPlay.setEnabled(false);
        lblTxtNumberOfRounds = new JLabel(bundle.getString("lbl.numberOfRounds") + ":");
        lblNumberOfRounds = new JLabel();
        lblTxtCurrentRound = new JLabel(bundle.getString("lbl.currentRound") + ":");
        lblCurrentRound = new JLabel();
        lblTxtNumberOfTurns = new JLabel(bundle.getString("lbl.numberOfTurns") + ":");
        lblNumberOfTurns = new JLabel();
        lblTxtCurrentTurn = new JLabel(bundle.getString("lbl.currentTurn") + ":");
        lblCurrentTurn = new JLabel();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlBoard, 630, 630, 630)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnInitBoard)
                                                .addComponent(btnSetRounds)
                                                .addComponent(btnPlay)
                                                .addComponent(scrBoardInfo)))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lblTxtNumberOfRounds)
                                                                .addComponent(lblTxtCurrentRound))
                                                        .addGap(28, 28, 28)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(lblCurrentRound)
                                                                .addComponent(lblNumberOfRounds)))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lblTxtNumberOfTurns)
                                                                .addComponent(lblTxtCurrentTurn))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lblCurrentTurn)
                                                                .addComponent(lblNumberOfTurns))))))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlBoard, 630, 630, 630)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(scrBoardInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnInitBoard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSetRounds)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPlay)
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTxtNumberOfRounds)
                                .addComponent(lblNumberOfRounds))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTxtCurrentRound)
                                .addComponent(lblCurrentRound))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTxtNumberOfTurns)
                                .addComponent(lblNumberOfTurns))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTxtCurrentTurn)
                                .addComponent(lblCurrentTurn)))
        );

        setVisible(true);
    }

    public void activateBtnInitBoard() {
        btnInitBoard.setEnabled(true);
    }

    public void btnSetRoundsEnabled(boolean flag) {
        btnSetRounds.setEnabled(flag);
    }

    public void btnPlayEnabled(boolean flag) {
        btnPlay.setEnabled(flag);
    }

    private void btnInitBoardActionPerformed() {
        dc.addPlayersToGame();
        registerGameAsListener();
        registerBoardAsListener();
        setSquareMouseListeners();
        final GameJPanel self = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dc.populateBoard(70);
                    JOptionPane.showMessageDialog(self, bundle.getString("msg.settlement"));
                    pnlBoard.setSettlementsEnabled(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        btnInitBoard.setEnabled(false);
    }

    public void registerBoardAsListener() {
        pnlBoard.registerAsListener();
    }

    public void registerGameAsListener() {
        dc.addRepoChangeListener(this);
        dc.addGameChangeListener(this);
    }

    private void btnSetRoundsActionPerformed() {
        boolean correctInput = false;
        while (!correctInput) {
            try {
                dc.setupRounds(Integer.parseInt(JOptionPane.showInputDialog(this, bundle.getString("msg.setRounds"))));
                correctInput = true;
            } catch (NumberFormatException exp) {
                JOptionPane.showMessageDialog(null, bundle.getString("exp.numberFormat"), bundle.getString("exp.incorrectInput"), JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException exp) {
                String[] message = exp.getMessage().split(" ");
                JOptionPane.showMessageDialog(null, String.format(bundle.getString(message[0]), message[1]), bundle.getString("exp.incorrectInput"), JOptionPane.ERROR_MESSAGE);
            }
        }
        btnSetRounds.setEnabled(false);
        lblNumberOfRounds.setText(Integer.toString(dc.getNumberOfRounds()));
        lblCurrentRound.setText(Integer.toString(dc.getCurrentRoundNumber()));
        setFinalizedRounds(0);
        for (boolean round : finalizedRounds) {
            round = false;
        }
        btnPlayEnabled(true);
        frmStart.btnSaveSetEnabled(true);
        frmStart.btnNewGameSetEnabled(true);
        frmStart.btnLoadSetEnabled(true);
    }

    public void setSquareMouseListeners() {
        pnlBoard.setSquareMouseListeners();
    }

    public void setFinalizedRounds(int currentRound) {
        finalizedRounds = new boolean[dc.getNumberOfRounds()];
        for (int i = 0; i < currentRound; i++) {
            finalizedRounds[i] = true;
        }
    }

    public void finalizeRound(int round) {
        finalizedRounds[round - 1] = true;
        if (finalizedRounds[dc.getNumberOfRounds() - 1]) {
            frmStart.btnSaveSetEnabled(false);
        } else {
            frmStart.btnSaveSetEnabled(true);
        }
        btnPlay.setText(bundle.getString("btn.nextRound"));
    }

    public boolean isRoundFinalized(int round) {
        return finalizedRounds[round - 1];
    }

    public void resetBoard() {
        pnlBoard.setBuyingCamelsEnabled(false);
        pnlBoard.setAdjacentEnabled(false);
        pnlBoard.setMovingEnabled(false);
        pnlBoard.showAllSquares(true);
    }

    public void resetSettlementsPlaced() {
        pnlBoard.resetSettlementsPlaced();
    }

    private void btnPlayActionPerformed() {
        btnPlay.setText(bundle.getString("btn.skip"));
        pnlBoard.showAllSquares(true);
        String[][] players = dc.getPlayers();
        if (!dc.isGameLoaded() && dc.getCurrentRoundNumber() > 0
                && (dc.getCurrentTurnNumber() == dc.getNumberOfTurns()
                && !isRoundFinalized(dc.getCurrentRoundNumber()))) {
            pnlBoard.finalizeTurn(players);
            pnlBoard.setBuyingCamelsEnabled(false);
            pnlBoard.setAdjacentEnabled(false);
            pnlBoard.setMovingEnabled(false);
        } else if (dc.isGameLoaded() || dc.getCurrentRoundNumber() == 0
                || dc.getCurrentTurnNumber() != dc.getNumberOfTurns() || dc.getCurrentRoundNumber() != dc.getNumberOfRounds()) {
            String[] playerNames = new String[players.length];
            for (int i = 0; i < players.length; i++) {
                playerNames[i] = players[i][0];
            }
            String[] nextTurn = dc.getNextTurn();
            if (dc.getCurrentTurnNumber() == 1) {
                frmStart.btnSaveSetEnabled(false);
                JOptionPane.showMessageDialog(this, String.format(bundle.getString("msg.roundStarted"), dc.getCurrentRoundNumber()));
                JOptionPane.showMessageDialog(this, String.format(bundle.getString("msg.diceroll")
                        + bundle.getString("msg.turnsthisround"), nextTurn[2], dc.getNumberOfTurns()));
                frmStart.setTotalTurnsEachPlayer(dc.getNumberOfTurns()/4);
                frmStart.resetTurnsPlayedPerPlayed();
                frmStart.updatePlayersPane();

            }
            if (nextTurn[0] != null) {
                JOptionPane.showMessageDialog(this, String.format(bundle.getString("msg.playerToMove"), nextTurn[0]));
                frmStart.incTurnsPlayed(nextTurn[0]);
                frmStart.updatePlayersPane();
                pnlBoard.showPlayerSquaresWithCamels(nextTurn[0]);
                pnlBoard.setBuyingCamelsEnabled(false);
                pnlBoard.setAdjacentEnabled(false);
                pnlBoard.setMovingEnabled(true);
            } else {
                pnlBoard.setBuyingCamelsEnabled(false);
                pnlBoard.setAdjacentEnabled(false);
                pnlBoard.setMovingEnabled(false);
                pnlBoard.showAllSquares(true);
                JOptionPane.showMessageDialog(this, bundle.getString(nextTurn[1]), bundle.getString("fortune"), JOptionPane.INFORMATION_MESSAGE);
                btnPlay.setText(bundle.getString("btn.nextTurn"));
            }
            lblCurrentRound.setText(Integer.toString(dc.getCurrentRoundNumber()));
            lblNumberOfTurns.setText(Integer.toString(dc.getNumberOfTurns()));
            lblCurrentTurn.setText(Integer.toString(dc.getCurrentTurnNumber()));
        }
    }

    public void setLblNumberOfTurns(String txt) {
        lblNumberOfTurns.setText(txt);
    }

    public void setLblCurrentTurn(String txt) {
        lblCurrentTurn.setText(txt);
    }

    public void setBtnPlayText(String txt) {
        btnPlay.setText(txt);
    }

    public void setBoardInfo() {
        String boardInfoTxt = String.format("%-31s\n"
                + "===============================\n" //31 x '='
                + "%-10s%21s\n"
                + "%-10s%21s\n"
                + "%-10s%21s\n"
                + "%-10s%21s\n",
                bundle.getString("legend"),
                bundle.getString("MAGENTA").substring(0,1).toUpperCase() + bundle.getString("MAGENTA").substring(1) + ":", "50 " + bundle.getString("camels").toLowerCase(),
                bundle.getString("RED").substring(0,1).toUpperCase() + bundle.getString("RED").substring(1) + ":", "10 " + bundle.getString("camels").toLowerCase(),
                bundle.getString("YELLOW").substring(0,1).toUpperCase() + bundle.getString("YELLOW").substring(1) + ":", "5 " + bundle.getString("camels").toLowerCase(),
                bundle.getString("GREEN").substring(0,1).toUpperCase() + bundle.getString("GREEN").substring(1) + ":", "1 " + bundle.getString("camel").toLowerCase());
        txaBoardInfo.setText(boardInfoTxt);
    }

    public void setBoardInfo(int sqX, int sqY) {
        String boardInfoTxt = "";
        if (!dc.isBoardFilled()) {
            boardInfoTxt = bundle.getString("msg.noInfo");
        } else {
            String[][][] board = dc.getBoard();
            boardInfoTxt += String.format(bundle.getString("region").toUpperCase() + ": %s\n"
                    + bundle.getString("econValue").toUpperCase() + ": %s\n"
                    + bundle.getString("stratValue").toUpperCase() + ": %s\n"
                    + bundle.getString("sector").toUpperCase() + ": %d\n"
                    + bundle.getString("player").toUpperCase() + ": %s\n"
                    + bundle.getString("camels").toUpperCase() + ": %s\n",
                    bundle.getString("region." + board[sqX][sqY][0].toLowerCase()), board[sqX][sqY][1], board[sqX][sqY][2],
                    Integer.parseInt(board[sqX][sqY][3]) + 1, board[sqX][sqY][4], board[sqX][sqY][6]);

        }
        txaBoardInfo.setText(boardInfoTxt);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        lblNumberOfRounds.setText(Integer.toString(dc.getNumberOfRounds()));
        lblCurrentRound.setText(Integer.toString(dc.getCurrentRoundNumber()));
    }
}
