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
                new MigLayout("wrap 7, fill, debug", "[fill]", "[fill]"));
        for (int i = 0; i < 42; i++){
            JPanel panel = new JPanel();
            panel.addMouseListener(new MyMouseListener());
            pnlBoard.add(panel);
        }
        pnlBoard.setBackground(Color.BLUE);
        add(pnlBoard);

        JPanel pnlBottom = new JPanel(new BorderLayout());
        pnlBottom.setPreferredSize(new Dimension(400, 150));
        add(pnlBottom, BorderLayout.SOUTH);

        JPanel pnlResult = new JPanel(new MigLayout("debug, filly"));
        pnlBottom.add(pnlResult, BorderLayout.WEST);

        lblRedWin = new JLabel("Rojo: 0");
        lblRedWin.setFont(lblRedWin.getFont().deriveFont(Font.BOLD));
        lblYellowWin = new JLabel("Amarillo: 0");
        lblYellowWin.setFont(lblYellowWin.getFont().deriveFont(Font.BOLD));

        pnlResult.add(lblRedWin, "wrap");
        pnlResult.add(lblYellowWin);

        JPanel pnlInfo = new JPanel(new MigLayout("debug, align 50% 50%"));
        pnlBottom.add(pnlInfo, BorderLayout.CENTER);

        lblInfo = new JLabel("Información: ");
        pnlInfo.add(lblInfo);

        JPanel pnlButton = new JPanel(
                new MigLayout("debug, aligny 50%", "", "[]30[]"));
        pnlBottom.add(pnlButton, BorderLayout.EAST);

        JButton btnResetGame = new JButton("Reiniciar partida");
        JButton btnResetWinCount = new JButton("Reiniciar contador");

        pnlButton.add(btnResetGame, "wrap, grow");
        pnlButton.add(btnResetWinCount, "grow");

        printPieces();
    }

    public void setController(WindowController controller){
        this.controller = controller;
    }

    private void setMessageToLabel(String message, JLabel label){
        label.setText("Información: " + message);
    }

    private void printPieces(){
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
    }

    public class MyMouseListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            int index = Arrays.asList(pnlBoard.getComponents()).indexOf(e.getComponent());
            Piece piece;
            if ((piece = controller.panelWasClicked(index)) != null)  {
                printPieces();
                if (controller.checkBoard(piece)) {
                    setMessageToLabel("¡¡Hay 4 en raya!!", BoardView.this.lblInfo);
                    System.out.println(piece);
                }
                else
                    setMessageToLabel("", BoardView.this.lblInfo);
            }
            else
                setMessageToLabel("No caben mas fichas en esta columna", BoardView.this.lblInfo);
        }
    }
}
