package xyz_UML;

import javax.swing.JLayeredPane;

public class UseCase extends ClassAndCaseBase {
    private OvalShape oval = new OvalShape(0, 0, 110, 50);  // draw oval at (0,0) relative to the oval JPanel

    public UseCase(JLayeredPane canvas, int x, int y) {
        super.xmin = x;
        super.ymin = y;
        super.xmax = x + 110;
        super.ymax = y + 50;

        oval.setBounds(x, y, 110, 50);  // set this oval JPanel to where the mouse is
        paintMyComponents(canvas);
        canvas.add(oval, new Integer(0));
    }

    @Override
    protected void paintMyComponents(JLayeredPane canvas) {
        oval.setBounds(super.xmin, super.ymin, 110, 50);
        canvas.repaint();
    }

    @Override
    protected void moveComponentFront(JLayeredPane canvas) {
        canvas.moveToFront(oval);
    }
}
