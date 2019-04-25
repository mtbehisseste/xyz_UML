package xyz_UML;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

public class Classes extends ClassAndCaseBase { 
    private ArrayList<JLabel> classesLabel = new ArrayList<JLabel>(3);

    public Classes(JLayeredPane canvas, int x, int y) {
        super.xmin = x;
        super.ymin = y;
        super.xmax = x + 100;
        super.ymax = y + 3 * 40;

        for (int i = 0; i < 3; i++) {
            JLabel tmpLabel = new JLabel();
            tmpLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            tmpLabel.setOpaque(true);
            tmpLabel.setBackground(Color.white);
            classesLabel.add(tmpLabel);
            canvas.add(tmpLabel, new Integer(0));
        }
        paintMyComponents(canvas);
    }

    @Override
    protected void paintMyComponents(JLayeredPane canvas) {
        for (int i = 0; i < 3; i++) {
            classesLabel.get(i).setBounds(super.xmin, super.ymin + i * 40, 100, 40);
        }
        moveComponentFront(canvas);
        canvas.repaint();
    }

    @Override
    protected void moveComponentFront(JLayeredPane canvas) {
        for (int i = 0; i < 3; i++) {
            canvas.moveToFront(classesLabel.get(i));
        }
    }
    
    @Override
    protected void changeName(String name) {
        classesLabel.get(0).setFont(new Font(null, 0, 15));
        classesLabel.get(0).setText(name);
        classesLabel.get(0).setHorizontalAlignment(JLabel.CENTER);
    }
}
