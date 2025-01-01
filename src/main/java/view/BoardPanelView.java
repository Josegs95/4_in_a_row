package main.java.view;

import main.java.controller.Controller;
import main.java.model.BoardModel;
import main.java.model.Piece;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * This class job is to create the GUI, the frame, containers and their components. This class get the data
 * from a model and communicate with the controller each time the user does an input.
 */

public class BoardPanelView extends JPanel {
    final private BoardModel MODEL;
    private Controller controller;

    final private JFrame FRAME;

    private JPanel pnlBoard;

    private JLabel lblRedWin;
    private JLabel lblYellowWin;
    private JLabel lblInfo;

    private JRadioButton rbtnPvP;
    private JRadioButton rbtnPvE;

    private Font baseFont;
    private Font boldBaseFont;

    final private String RESET_GAME_TEXT = "Reiniciar partida";
    final private String RESET_SESSION_TEXT = "Reiniciar sesión";

    /**
     * Create a BoardPanelView object and sets a model.
     *
     * @param parent the JFrame on which this panel is added. Equivalent to (JFrame) this.getParent();
     * @param model the model of which get all the data.
     */
    public BoardPanelView(JFrame parent, BoardModel model){
        this.FRAME = parent;
        this.MODEL = model;

        init();
    }

    private void init(){
        //Look and feel
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        FRAME.setJMenuBar(menuBar);

        JMenu menuGame = new JMenu("Partida");
        JMenu menuOption = new JMenu("Opciones");
        menuBar.add(menuGame);
        menuBar.add(menuOption);

        JMenuItem mnItemResetGame = new JMenuItem(RESET_GAME_TEXT);
        JMenuItem mnItemResetSession = new JMenuItem(RESET_SESSION_TEXT);
        JMenuItem mnItemExit = new JMenuItem("Salir");
        menuGame.add(mnItemResetGame);
        menuGame.add(mnItemResetSession);
        menuGame.add(mnItemExit);

        JMenu menuMode = new JMenu("Modo");
        menuOption.add(menuMode);

        rbtnPvP = new JRadioButton("PvP");
        rbtnPvE = new JRadioButton("PvE");
        ButtonGroup btnGMode = new ButtonGroup();
        btnGMode.add(rbtnPvP);
        btnGMode.add(rbtnPvE);
        rbtnPvP.setSelected(true);
        menuMode.add(rbtnPvP);
        menuMode.add(rbtnPvE);

        pnlBoard = new JPanel(
                new MigLayout("wrap 7, fill", "[fill]", "[fill]"));
        for (int i = 0; i < 42; i++){
            JPanel panel = new JPanel();
            panel.addMouseListener(new BoardMouseListener());
            pnlBoard.add(panel);
        }
        pnlBoard.setBackground(Color.decode("#005254"));
        add(pnlBoard, BorderLayout.CENTER);

        JPanel pnlBottom = new JPanel(new BorderLayout());
        pnlBottom.setPreferredSize(new Dimension(400, 150));
        add(pnlBottom, BorderLayout.SOUTH);

        JPanel pnlResult = new JPanel(new MigLayout("insets 0 30 0, aligny center",
                "", "[]20[]"));
        pnlBottom.add(pnlResult, BorderLayout.WEST);

        lblRedWin = new JLabel();
        lblYellowWin = new JLabel();

        baseFont = lblRedWin.getFont().deriveFont(Font.PLAIN);
        boldBaseFont = baseFont.deriveFont(Font.BOLD);

        pnlResult.add(lblRedWin, "wrap");
        pnlResult.add(lblYellowWin);

        JPanel pnlInfo = new JPanel(new MigLayout("align 50% 50%"));
        pnlBottom.add(pnlInfo, BorderLayout.CENTER);

        lblInfo = new JLabel("Información: ");
        pnlInfo.add(lblInfo);

        JPanel pnlButton = new JPanel(
                new MigLayout("aligny 50%", "", "[]30[]"));
        pnlBottom.add(pnlButton, BorderLayout.EAST);

        JButton btnResetGame = new JButton(RESET_GAME_TEXT);
        JButton btnResetWinCount = new JButton(RESET_SESSION_TEXT);

        pnlButton.add(btnResetGame, "wrap, grow");
        pnlButton.add(btnResetWinCount, "grow");

        //Listeners

        ActionListener softResetListener = (e -> controller.resetGameButtonPressed());
        ActionListener hardResetListener = (e -> controller.resetWinCountButtonPressed());

        btnResetGame.addActionListener(softResetListener);
        mnItemResetGame.addActionListener(softResetListener);

        btnResetWinCount.addActionListener(hardResetListener);
        mnItemResetSession.addActionListener(hardResetListener);

        rbtnPvP.addActionListener(new ChoosingModeActionListener("PVP"));
        rbtnPvE.addActionListener(new ChoosingModeActionListener("PVE"));

        menuGame.setMnemonic(KeyEvent.VK_P);
        mnItemResetGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        mnItemResetSession.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        mnItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));

        mnItemExit.addActionListener((e -> FRAME.dispose()));

        printBoard();
        updateScores();
    }

    /**
     * Set the controller to the view
     *
     * @param controller the controller of the view
     */
    public void setController(Controller controller){
        this.controller = controller;
    }

    /**
     * Reset the board, with all their pieces to start a new game.
     */
    public void resetGame(){
        printBoard();
        lblInfo.setText("Información: ");
        pnlBoard.setEnabled(true);
    }

    /**
     * Reset the board and the scores
     */
    public void resetAll(){
        updateScores();
        resetGame();
    }

    private void printBoard(){
        Piece[][] board = MODEL.getBoard();
        for (int i = 0; i < pnlBoard.getComponents().length; i++){
            int y = i / board[0].length;
            int x = i % board[0].length;

            try{
                JPanel panel = (JPanel) pnlBoard.getComponent(i);

                if (board[y][x] == null){
                    panel.setBackground(Color.WHITE);
                    continue;
                }

                switch (board[y][x].getType()){
                    case RED -> panel.setBackground(Color.RED);
                    case YELLOW -> panel.setBackground(Color.YELLOW);
                }
            } catch (Exception e) {
                System.out.println("i: " + i);
                System.out.println("x: " + x);
                System.out.println("y: " + y);
            }
        }

        pnlBoard.repaint();
        updateTurn();
    }

    private void setWinner(){
        pnlBoard.setEnabled(false);
        String infoText = "<html><div align=center>Información: partida terminada.<br>Pulse el botón '" +
                RESET_GAME_TEXT + "' para empezar otra.</div></html>";
        lblInfo.setText(infoText);
        String winner = MODEL.isRedTurn() ? "rojo" : "amarillo";
        JOptionPane.showMessageDialog(this, "¡4 en raya! El equipo " + winner + " ha ganado.",
                "Partida finalizada", JOptionPane.INFORMATION_MESSAGE);
        updateScores();
    }

    private void updateTurn(){
        if (MODEL.isRedTurn()) {
            lblRedWin.setFont(boldBaseFont);
            lblYellowWin.setFont(baseFont);
        } else {
            lblYellowWin.setFont(boldBaseFont);
            lblRedWin.setFont(baseFont);
        }
    }

    private void updateScores(){
        lblRedWin.setText("Rojo: " + MODEL.getRedWins());
        lblYellowWin.setText("Amarillo: " + MODEL.getYellowWins());
    }

    private class BoardMouseListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            if (pnlBoard.isEnabled()){
                int index = Arrays.asList(pnlBoard.getComponents()).indexOf(e.getComponent());
                int targetColumn = index % MODEL.getWIDTH();
                int response = controller.panelWasClicked(targetColumn);
                if (response == -1)
                    return;

                printBoard();

                if (response == 1) {
                    setWinner();
                    return;
                }

                if (MODEL.isModePvE()){
                    pnlBoard.setEnabled(false);
                    createRandomPiece();
                }
            }
        }

        private void createRandomPiece(){
            new Thread(() -> {
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                int response = controller.panelWasClicked(MODEL.getRandomAvailableColumn());

                printBoard();
                pnlBoard.setEnabled(true);

                if (response == 1)
                    setWinner();
            }).start();
        }
    }

    private class ChoosingModeActionListener implements ActionListener{

        final private String MODE;

        ChoosingModeActionListener(String mode){
            this.MODE = mode.toUpperCase();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (MODE.equals("PVP") ? !MODEL.isModePvE() : MODEL.isModePvE()) {
                cancelChange();
                return;
            }

            int option = JOptionPane.showConfirmDialog(FRAME,
                    "Se va a reiniciar el tablero y los marcadores para activar el modo " + MODE + ". ¿Estás seguro?",
                    "Activar modo " + MODE,
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.NO_OPTION) {
                cancelChange();
                return;
            }

            MODEL.setModePvE(MODE.equals("PVE"));
            controller.resetWinCountButtonPressed();
        }

        private void cancelChange(){
            if (MODE.equals("PVP"))
                rbtnPvE.setSelected(true);
            else
                rbtnPvP.setSelected(true);
        }
    }
}
