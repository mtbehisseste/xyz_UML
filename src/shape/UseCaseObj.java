package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import editor.Canvas;
import editor.EditorFrame;

import javax.swing.JComponent;

public class UseCaseObj extends BasicObj {
    private JComponent oval = new JComponent() {  // draw oval at (0,0) relative to the oval JComponent
        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.lightGray);
            g2.fillOval(0, 0, 110, 50);  // internal of the oval
            g2.setStroke(new BasicStroke(1.5f));
            g2.setColor(Color.black);
            g2.drawOval(0, 0, 110, 50);  // border of the oval
        }
    };
    private JLabel ovalName = new JLabel();

    public UseCaseObj(Canvas canvas, int x, int y) {
        super.xmin = x;
        super.ymin = y;
        super.xmax = x + 110;
        super.ymax = y + 50;

        oval.setBounds(x, y, 110, 50);  // set this oval JPanel to where the mouse is
        ovalName.setBounds(x, y, 110, 50);
        canvas.add(oval, new Integer(0));
        canvas.add(ovalName, new Integer(0));
        paintMyComponents(canvas);
    }

    @Override
    public void paintMyComponents(Canvas canvas) {
        oval.setBounds(super.xmin, super.ymin, 110, 50);
        ovalName.setBounds(super.xmin, super.ymin, 110, 50);
        canvas.repaint();
    }

    @Override
    public void moveComponentFront(Canvas canvas) {
        canvas.moveToFront(oval);
        canvas.moveToFront(ovalName);
    }

    @Override
    public void changeName(String name) {
        ovalName.setFont(new Font(null, 0, 15));
        ovalName.setText(name);
        ovalName.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public void select(Canvas canvas) {
        EditorFrame.editMenuChangeName.setEnabled(true);
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
