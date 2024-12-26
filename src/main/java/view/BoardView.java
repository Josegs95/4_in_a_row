package main.java.view;

import main.java.controller.WindowController;
import main.java.model.BoardModel;
import main.java.model.Piece;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class BoardView extends JFrame {
    private BoardModel model;
    private WindowController controller;

    private JPanel pnlBoard;
    private JLabel lblRedWin;
    private JLabel lblYellowWin;
    private JLabel lblInfo;

    private Font baseFont;
    private Font boldBaseFont;

    public BoardView(String title, BoardModel model){
        super(title);
        this.model = model;

        init();
    }

    void init(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);

        pnlBoard = new JPanel(
                new MigLayout("wrap 7, fill", "[fill]", "[fill]"));
        for (int i = 0; i < 42; i++){
            JPanel panel = new JPanel();
            panel.addMouseListener(new BoardMouseListener());
            pnlBoard.add(panel);
        }
        pnlBoard.setBackground(Color.BLUE);
        add(pnlBoard);

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

        JButton btnResetGame = new JButton("Reiniciar partida");
        JButton btnResetWinCount = new JButton("Reiniciar contador");
        btnResetGame.addActionListener(e -> controller.resetGameButtonPressed());
        btnResetWinCount.addActionListener(e -> controller.resetWinCountButtonPressed());

        pnlButton.add(btnResetGame, "wrap, grow");
        pnlButton.add(btnResetWinCount, "grow");

        printBoard();
        updateScores();
    }

    public void setController(WindowController controller){
        this.controller = controller;
    }

    public void printBoard(){
        Piece[][] board = model.getBoard();
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

    public void resetGame(){
        printBoard();
        lblInfo.setText("Información: ");
    }

    public void resetAll(){
        updateScores();
        resetGame();
    }

    private void setWinner(){
        pnlBoard.setEnabled(false);
        String winner = model.isRedTurn() ? "rojo" : "amarillo";
        JOptionPane.showMessageDialog(this, "¡4 en raya! El equipo " + winner + " ha ganado.",
                "Partida finalizada", JOptionPane.INFORMATION_MESSAGE);
        lblInfo.setText("Información: partida terminada.");

        updateScores();
    }

    private void updateTurn(){
        if (model.isRedTurn()) {
            lblRedWin.setFont(boldBaseFont);
            lblYellowWin.setFont(baseFont);
        } else {
            lblYellowWin.setFont(boldBaseFont);
            lblRedWin.setFont(baseFont);
        }
    }

    private void updateScores(){
        lblRedWin.setText("Rojo: " + model.getRedWins());
        lblYellowWin.setText("Amarillo: " + model.getYellowWins());
    }



    public class BoardMouseListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            int index = Arrays.asList(pnlBoard.getComponents()).indexOf(e.getComponent());
            boolean response = controller.panelWasClicked(index);

            if (response)
                setWinner();
        }
    }
}
