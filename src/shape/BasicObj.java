package shape;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

import editor.Canvas;

public class BasicObj extends Shape {
    protected ArrayList<JLabel> portsArray = new ArrayList<JLabel>();

    public void showPorts(Canvas canvas) {  // calculate the position of the four ports then add to the canvas
        // order: north, east, south, west
        int[] xoffset = { (super.xmax - super.xmin) / 2 - 5,
            (super.xmax - super.xmin) - 5,
            (super.xmax - super.xmin) / 2 - 5,
            -5 };
        int[] yoffset = { -5,
            (super.ymax - super.ymin) / 2 - 5,
            (super.ymax - super.ymin) - 5,
            (super.ymax - super.ymin) / 2 - 5 };

        for (int i = 0; i < 4; i++) {
            JLabel tmpPortLabel = new JLabel();
            tmpPortLabel.setBounds(super.xmin + xoffset[i], super.ymin + yoffset[i], 10, 10);
            tmpPortLabel.setOpaque(true);
            tmpPortLabel.setBackground(Color.black);
            portsArray.add(tmpPortLabel);
            canvas.add(tmpPortLabel, new Integer(2));
        }
        canvas.repaint();
    }

    public void hidePorts(Canvas canvas) {
        for (int i = 0; i < this.portsArray.size(); i++)
            canvas.remove(this.portsArray.get(i));
        canvas.repaint();
        portsArray.clear();
    }

    public void select(Canvas canvas) {

    }

    public void move(Canvas canvas, int xmove, int ymove) {
        this.xmax += xmove;
        this.xmin += xmove;
        this.ymax += ymove;
        this.ymin += ymove;
    }

    public void moveComponentFront(Canvas canvas) {

    }

    public void changeName(String name) {

    }

    public boolean checkIfOnComponent(int x, int y) {
        return false;
    }
}
