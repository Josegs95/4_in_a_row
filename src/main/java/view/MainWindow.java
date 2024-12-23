package main.java.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private String title;

    public MainWindow(String title){
        super(title);
        this.title = title;

        init();
    }

    private void init(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);

        JPanel pnlBoard = new JPanel(
                new MigLayout("wrap 7, fill, debug", "[fill]", "[fill]"));
        for (int i = 0; i < 42; i++){
            JPanel panel = new JPanel();
            panel.setBackground(i % 2 == 0 ? Color.RED : Color.YELLOW);

            pnlBoard.add(panel);
        }
        pnlBoard.setBackground(Color.BLUE);
        add(pnlBoard);

        JPanel pnlBottom = new JPanel(new BorderLayout());
        pnlBottom.setPreferredSize(new Dimension(400, 150));
        add(pnlBottom, BorderLayout.SOUTH);

        JPanel pnlResult = new JPanel(new MigLayout("debug, filly"));
        pnlBottom.add(pnlResult, BorderLayout.WEST);

        JLabel lblRedWin = new JLabel("Rojo: 0");
        lblRedWin.setFont(lblRedWin.getFont().deriveFont(Font.BOLD));
        JLabel lblYellowWin = new JLabel("Amarillo: 0");
        lblYellowWin.setFont(lblYellowWin.getFont().deriveFont(Font.BOLD));

        pnlResult.add(lblRedWin, "wrap");
        pnlResult.add(lblYellowWin);

        JPanel pnlInfo = new JPanel(new MigLayout("debug, align 50% 50%"));
        pnlBottom.add(pnlInfo, BorderLayout.CENTER);

        JLabel lblInfo = new JLabel("Información: ");
        pnlInfo.add(lblInfo);

        JPanel pnlButton = new JPanel(
                new MigLayout("debug, aligny 50%", "", "[]30[]"));
        pnlBottom.add(pnlButton, BorderLayout.EAST);

        JButton btnResetGame = new JButton("Reiniciar partida");
        JButton btnResetWinCount = new JButton("Reiniciar contador");

        pnlButton.add(btnResetGame, "wrap, grow");
        pnlButton.add(btnResetWinCount, "grow");

        //pack();
    }
}
