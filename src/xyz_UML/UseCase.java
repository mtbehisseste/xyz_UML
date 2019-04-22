package xyz_UML;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class UseCase extends ClassAndCaseBase {
    private OvalShape oval = new OvalShape(0, 0, 110, 50);  // draw oval at (0,0) relative to the oval JPanel
    private JLabel ovalName = new JLabel();

    public UseCase(JLayeredPane canvas, int x, int y) {
        super.xmin = x;
        super.ymin = y;
        super.xmax = x + 110;
        super.ymax = y + 50;

        oval.setBounds(x, y, 110, 50);  // set this oval JPanel to where the mouse is
        ovalName.setBounds(x, y, 110, 50);
        paintMyComponents(canvas);
        canvas.add(oval, new Integer(0));
        canvas.add(ovalName, new Integer(0));
    }

    @Override
    protected void paintMyComponents(JLayeredPane canvas) {
        oval.setBounds(super.xmin, super.ymin, 110, 50);
        ovalName.setBounds(super.xmin, super.ymin, 110, 50);
        canvas.repaint();
    }

    @Override
    protected void moveComponentFront(JLayeredPane canvas) {
        canvas.moveToFront(oval);
        canvas.moveToFront(ovalName);
    }
    
    @Override
    protected void changeName(String name) {
        ovalName.setFont(new Font(null, 0, 15));
        ovalName.setText(name);
        ovalName.setHorizontalAlignment(JLabel.CENTER);
    }
}
