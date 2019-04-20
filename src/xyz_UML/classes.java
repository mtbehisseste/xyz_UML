package xyz_UML;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class classes extends classAndCaseBase {
    private ArrayList<JLabel> classesLabel = new ArrayList<JLabel>(3);

    public classes(JLayeredPane canvas, int x, int y) {
        super.xmin = x;
        super.ymin = y;
        super.xmax = x + 100;
        super.ymax = y + 3 * 40;

        for (int i = 0; i < 3; i++) {
            JLabel tmpLabel = new JLabel();
            tmpLabel.setBounds(x, y + i * 40, 100, 40);
            tmpLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            tmpLabel.setOpaque(true);
            tmpLabel.setBackground(Color.white);
            classesLabel.add(tmpLabel);
            canvas.add(tmpLabel);
            canvas.repaint();
        }
    }
}
