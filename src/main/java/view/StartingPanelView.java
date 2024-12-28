package main.java.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StartingPanelView extends JPanel {

    private int cont = 0;
    private String[] fontName;

    public StartingPanelView(){
        super();
        init();
    }

    private void init(){
        setLayout(new MigLayout("fill"));

        String title = "Bienvenido al 4 en raya";

        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setForeground(Color.YELLOW);
        lblTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 32));

        JPanel pnlMain = new JPanel(new MigLayout());
        JLabel lblInfo = new JLabel("<html><div align=center>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam et dui libero. Cras mollis enim eget erat consequat fermentum. Integer placerat sed dolor sed blandit. Aenean vulputate mi at posuere cursus. Fusce non urna tellus. Ut ultricies sapien vestibulum massa tempus, in elementum purus lobortis. Maecenas eros lectus, fringilla bibendum sollicitudin consectetur, efficitur ut metus. Mauris placerat tempus suscipit. Morbi ultrices tortor vitae pellentesque fringilla. Nullam eu tristique elit, sed feugiat justo. Suspendisse lorem risus, mollis non imperdiet nec, eleifend sed neque. Sed elementum, erat eu auctor efficitur, leo felis tempus lorem, eget consectetur metus lectus ut eros. Orci varius.</div></html>");
        lblInfo.setBorder(new LineBorder(Color.CYAN, 1, true));

        JPanel pnlButton = new JPanel(new MigLayout("fill"));
        JButton btnStart = new JButton("Comenzar");

        add(lblTitle, "north, gapy 20 0");
        add(pnlMain, "dock center");

        pnlMain.add(lblInfo, "dock center, gapx 100 100");
        pnlMain.add(pnlButton, "east");

        pnlButton.add(btnStart, "gapx 10 30, aligny 80%");

        setBackground(Color.GRAY);
        pnlMain.setOpaque(false);
        pnlButton.setOpaque(false);
        btnStart.setOpaque(false);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontName = ge.getAvailableFontFamilyNames();

        btnStart.addActionListener(e -> lblTitle.setFont(getNextFont()));
    }

    private Font getNextFont(){
        if (cont == fontName.length)
            cont = 0;
        System.out.println(fontName[cont]);
        Font font = new Font(fontName[cont++], Font.BOLD, 32);

        return font;
    }
}
