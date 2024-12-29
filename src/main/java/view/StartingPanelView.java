package main.java.view;

import main.java.controller.Controller;
import main.java.model.BoardModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StartingPanelView extends JPanel {

    final private JFrame FRAME;

    private int cont = 0;
    private String[] fontName;

    public StartingPanelView(JFrame parent){
        super();

        this.FRAME = parent;

        init();
    }

    private void init(){
        setLayout(new MigLayout("fill"));

        String title = "Bienvenido al 4 en raya";

        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setForeground(Color.YELLOW);
        lblTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 42));

        JPanel pnlMain = new JPanel(new MigLayout("filly", "[grow 70][grow 30]", ""));
        JLabel lblInfo = new JLabel(getHTMLText(getMessageToUser()));
        lblInfo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.RED, 1, true), new EmptyBorder(5, 5, 5, 5)));
        lblInfo.setFont(lblInfo.getFont().deriveFont(20f));

        JPanel pnlButton = new JPanel(new MigLayout("fill"));
        JButton btnPlay = new JButton("Jugar");
        btnPlay.setMinimumSize(new Dimension(100, 20));

        add(lblTitle, "north, gapy 20 0");
        add(pnlMain, "dock center");

        pnlMain.add(lblInfo, "gapx 50 50, aligny 30%");
        pnlMain.add(btnPlay, "gapx 0 30, aligny 80%");

        //pnlButton.add(btnPlay, "gapx 0 30, aligny 80%");

        setBackground(Color.decode("#005254"));
        pnlMain.setOpaque(false);
        //pnlButton.setOpaque(false);
        btnPlay.setOpaque(false);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontName = ge.getAvailableFontFamilyNames();

        btnPlay.addActionListener(e -> {
            FRAME.getContentPane().removeAll();

            BoardModel model = new BoardModel();
            BoardPanelView boardPanel = new BoardPanelView(FRAME, model);
            Controller controller = new Controller(boardPanel, model);
            boardPanel.setController(controller);

            FRAME.getContentPane().add(boardPanel);
            FRAME.validate();
        });
    }

    private String getHTMLText(String text){
        return "<html><div align=center>" + text + "</div></html>";
    }

    private String getMessageToUser(){
        return "¡Hola!, espero que disfrutes del juego que he diseñado. El juego es muy simple así que resumiré sus reglas:" +
                "<br><ol>" +
                "<li>Se juega con dos jugadores, uno contra el otro.</li>" +
                "<li>Empieza siempre las fichas rojas.</li>" +
                "<li>Gana quien primero consiga un 4 en raya.</li>" +
                "<li>Puedes reiniciar la partida solo o la partida y contadores de victorias.</li>" +
                "<ol>";
    }
}
