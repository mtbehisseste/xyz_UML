package shape;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import editor.Canvas;
import editor.EditorFrame;

public class ClassesObj extends BasicObj {
    private ArrayList<JLabel> classesLabel = new ArrayList<JLabel>(3);

    public ClassesObj(Canvas canvas, int x, int y) {
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
    public void paintMyComponents(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            classesLabel.get(i).setBounds(super.xmin, super.ymin + i * 40, 100, 40);
        }
        moveComponentFront(canvas);
        canvas.repaint();
    }

    @Override
    public void moveComponentFront(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            canvas.moveToFront(classesLabel.get(i));
        }
    }

    @Override
    public void changeName(String name) {
        classesLabel.get(0).setFont(new Font(null, 0, 15));
        classesLabel.get(0).setText(name);
        classesLabel.get(0).setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public void select(Canvas canvas) {
        EditorFrame.setEditMenuChangeName(true);
        moveComponentFront(canvas);
        canvas.switchTopComponentToArraylistHead(this);
        showPorts(canvas);
    }

    @Override
    public boolean checkIfOnComponent(int x, int y) {
        if (x >= this.xmin &&
                x <= this.xmax &&
                y >= this.ymin &&
                y <= this.ymax) {
            return true;
        }
        return false;
    }
}
